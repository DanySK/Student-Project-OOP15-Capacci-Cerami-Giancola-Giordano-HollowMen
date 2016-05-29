package hollowmen.model.enemy;

import java.util.Collection;

import java.util.LinkedList;


import hollowmen.enumerators.ParamName;
import hollowmen.model.Enemy;
import hollowmen.model.Parameter;
import hollowmen.model.dungeon.ParamImpl;
import hollowmen.utilities.ExceptionThrower;

public abstract class EnemyBuilderImpl implements EnemyBuilder{

	protected String descritpion;
	protected int level;
	protected String title;
	protected Collection<Parameter> parameters;
	

	@Override
	public EnemyBuilder description(String description) {
		this.descritpion = description;
		return this;
	}

	@Override
	public EnemyBuilder level(int level) {
		this.level = level;
		return this;
	}

	@Override
	public EnemyBuilder title(String title) {
		this.title = title;
		return this;
	}

	@Override
	public EnemyBuilder param(Collection<Parameter> parameters) {
		this.parameters = new LinkedList<>();
		parameters.stream().forEach(p -> this.parameters.add(new ParamImpl(p)));
		return this;
	}

	public void standardException(Enemy e) throws IllegalStateException{
		ExceptionThrower.checkIllegalState(e.getLevel(), l -> l <= 0 || l >= 20);
		ExceptionThrower.checkIllegalState(e.getParameters(), p -> p.containsKey(ParamName.HPMAX.toString()) 
				&& p.get(ParamName.HPMAX).getValue() > 0 );
		ExceptionThrower.checkIllegalState(e.getParameters(), p -> p.containsKey(ParamName.DEFENSE.toString()));
		ExceptionThrower.checkIllegalState(e.getParameters(), p -> p.containsKey(ParamName.ATTACK.toString()));		
		ExceptionThrower.checkIllegalState(e.getParameters(), p -> p.containsKey(ParamName.ATTACKRANGE.toString()));
		ExceptionThrower.checkIllegalArgument(e.getParameters(), p -> p.containsKey(ParamName.ATTACKSPEED.toString())
				&& p.get(ParamName.ATTACKSPEED.toString()).getValue() > 0);
		ExceptionThrower.checkIllegalArgument(e.getParameters(), p -> p.containsKey(ParamName.MOVSPEED.toString()));
	}
	
	@Override
	public abstract Enemy build() throws IllegalStateException;

}






