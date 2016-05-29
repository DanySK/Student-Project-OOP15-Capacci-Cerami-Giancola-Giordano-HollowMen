package hollowmen.model.roomentity.hero;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import hollowmen.model.Modifier;
import hollowmen.model.Parameter;
import hollowmen.model.TargetPointSystem;
import hollowmen.model.dungeon.ModifierImpl;
import hollowmen.model.utils.Constants;
import hollowmen.model.utils.Counter;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

public class StatPointSystem implements TargetPointSystem<Parameter>{

	private int point;
	private Map<String, Pair<Parameter, Counter>> system = new HashMap<>();
	
	
	public StatPointSystem(Collection<Parameter> targets) {
		targets.stream().forEach(p -> this.system.put(p.getInfo().getName(),
				new Pair<>(p, new Counter())));
	}
	
	public StatPointSystem(Collection<Pair<Parameter, Integer>> targetsWithPoints, int unspentPoint) {
		for(Pair<Parameter, Integer> p : targetsWithPoints) {
			this.system.put(p.getX().getInfo().getName(), new Pair<>(p.getX(), new Counter()));
			for(int i = 0; i < p.getY(); i++) {
				this.spendPointOn(p.getX());
			}
		}
		this.point = unspentPoint;
	}
	
	@Override
	public Collection<Parameter> getTargets() {
		return this.system.entrySet().stream()
				.map(e -> e.getValue().getX())
				.collect(Collectors.toList());
	}

	@Override
	public int getUnspentPoint() {
		return this.point;
	}

	@Override
	public void addPoint(int pointToAdd) {
		this.point += pointToAdd;
	}

	@Override
	public void spendPointOn(Parameter target) throws IllegalStateException, IllegalArgumentException {
		ExceptionThrower.checkIllegalState(this.point, p -> p <= 0);
		ExceptionThrower.checkIllegalArgument(target, t -> !this.system.containsKey(keyGen(t)));
		this.system.get(keyGen(target)).getX().addModifier(this.generateMod(target));
	}

	@Override
	public void retrievePointFrom(Parameter target) throws IllegalArgumentException {
		ExceptionThrower.checkIllegalArgument(target, t -> !this.system.containsKey(keyGen(t)));
		ExceptionThrower.checkIllegalState(target, t -> this.system.get(keyGen(t)).getY().getCount() <= 0);
		this.system.get(keyGen(target)).getX().removeModifier(this.generateMod(target));
	}

	private Modifier generateMod(Parameter target) {
		return new ModifierImpl(keyGen(target), Constants.STAT_INCREASE, Constants.DEFAULT_OP);
	}
	
	private String keyGen(Parameter p) {
		return p.getInfo().getName();
	}
	
}
