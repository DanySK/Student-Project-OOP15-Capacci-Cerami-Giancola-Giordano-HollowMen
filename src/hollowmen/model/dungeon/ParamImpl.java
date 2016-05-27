package hollowmen.model.dungeon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

import hollowmen.model.Information;
import hollowmen.model.Modifier;
import hollowmen.model.Parameter;
import hollowmen.model.utils.InformationUserImpl;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

public class ParamImpl extends InformationUserImpl implements Parameter{
	
	private Map<Pair<BinaryOperator<Double>, Double>, List<Modifier>> modifiersMap = new HashMap<>();
	
	private double baseValue;

	private double sum = 0;
	private double mul = 1;
	
	
	public ParamImpl(Information info, double baseValue) {
		super(info);
		this.baseValue = baseValue;
	}
	
	@Override
	public double getValue() {
		return (baseValue + sum) * mul;
	}

	@Override
	public void addModifier(Modifier mod) throws IllegalArgumentException, NullPointerException {
		ExceptionThrower.checkNullPointer(mod);
		ExceptionThrower.checkIllegalArgument(mod, m -> this.getInfo().getName().equals(m));
		this.getOrPrepareList(mod).add(mod);
		
		if(mod.getOperation().equals(Modifier.Operation.ADD.getOp())) {
			mod.getOperation().apply(sum, mod.getParameter().getValue());
		}
		if(mod.getOperation().equals(Modifier.Operation.MUL.getOp())) {
			mod.getOperation().apply(mul, mod.getParameter().getValue());
		}
	}

	
	@Override
	public void removeModifier(Modifier mod) throws IllegalArgumentException, NullPointerException {
		ExceptionThrower.checkNullPointer(mod);
		ExceptionThrower.checkIllegalArgument(mod, m -> this.getInfo().getName().equals(m));
		this.getOrPrepareList(mod).remove(mod);
		
		if(mod.getOperation().equals(Modifier.Operation.ADD.getOp())) {
			mod.getReverseOperation().apply(sum, mod.getParameter().getValue());
		}
		if(mod.getOperation().equals(Modifier.Operation.MUL.getOp())) {
			mod.getReverseOperation().apply(mul, mod.getParameter().getValue());
		}
	}
	
	
	private List<Modifier> getOrPrepareList(Modifier mod) {
		return this.modifiersMap.merge(this.keyGen(mod), new LinkedList<>(), (x, y) -> x);
	}
	
	
	private Pair<BinaryOperator<Double>, Double> keyGen(Modifier mod) {
		return new Pair<>(mod.getOperation(), mod.getParameter().getValue());
	}
	
}
