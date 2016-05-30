package hollowmen.model.roomentity;

import hollowmen.model.LimitedCounter;
import hollowmen.model.Modifier;
import hollowmen.model.Parameter;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.dungeon.ModifierImpl;
import hollowmen.model.dungeon.ParamImpl;
import hollowmen.model.utils.LowerLimitReachException;
import hollowmen.model.utils.SimpleLimitedCounter;
import hollowmen.model.utils.UpperLimitReachException;
import hollowmen.utilities.ExceptionThrower;

public class HPclass extends ParamImpl{

	private Parameter maxHP;
	private LimitedCounter health;
	private double lastMaxHP;
	
	public HPclass(Parameter maxHP) {
		super(new InfoImpl(ParamName.HP.toString()), maxHP.getValue());
		this.maxHP = maxHP;
		this.lastMaxHP = maxHP.getValue();
		this.health = new SimpleLimitedCounter(maxHP.getValue(), maxHP.getValue());
	}

	@Override
	public double getValue() {
		if(lastMaxHP != maxHP.getValue()) {
			if(maxHP.getValue() - this.lastMaxHP < 0) {
				this.removeModifier(new ModifierImpl(ParamName.HP.toString(),
						this.lastMaxHP - maxHP.getValue(), Modifier.Operation.ADD));
				this.lastMaxHP = maxHP.getValue();
			} else {
				this.addModifier(new ModifierImpl(ParamName.HP.toString(),
						 maxHP.getValue() - this.lastMaxHP, Modifier.Operation.ADD));
				this.lastMaxHP = maxHP.getValue();
			} 
		}
		return this.getValue();
	}

	@Override
	public void addModifier(Modifier mod) throws IllegalArgumentException, NullPointerException {
		ExceptionThrower.checkNullPointer(mod);
		ExceptionThrower.checkIllegalArgument(mod, m -> this.getInfo().getName().equals(m));
		try {
			if(mod.getOperation().equals(Modifier.Operation.ADD)) {
				this.health.addToValue(mod.getParameter().getValue());
			} else {
				this.health.addToValue(mod.getOperation().apply(this.health.getValue(), mod.getParameter().getValue()));
			}
		} catch (UpperLimitReachException e) {};

	}

	@Override
	public void removeModifier(Modifier mod) throws IllegalArgumentException, NullPointerException {
		ExceptionThrower.checkNullPointer(mod);
		ExceptionThrower.checkIllegalArgument(mod, m -> this.getInfo().getName().equals(m));
		try {
			if(mod.getOperation().equals(Modifier.Operation.ADD)) {
				this.health.subToValue(mod.getParameter().getValue());
			} else {
				this.health.subToValue(mod.getOperation().apply(this.health.getValue(), mod.getParameter().getValue()));
			}
		} catch (LowerLimitReachException e) {
			//TODO destroy this
		};
		
	}
}
