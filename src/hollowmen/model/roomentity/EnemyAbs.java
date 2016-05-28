package hollowmen.model.roomentity;

import java.util.Collection;

import hollowmen.model.Enemy;
import hollowmen.model.Information;
import hollowmen.model.Parameter;

public abstract class EnemyAbs extends ActorAbs implements Enemy{

	private MovePattern pattern;
	private int combatLevel;
	private String title;
	
	
	public EnemyAbs(Information info, Collection<Parameter> param, int power, String title) {
		super(info, param);
		this.combatLevel = power;
		this.title = title;
	}
	
	public void move() {
		move(pattern.getDirection());
	}

	public MovePattern getPattern() {
		return pattern;
	}

	public void setPattern(MovePattern pattern) {
		this.pattern = pattern;
	}

	@Override
	public int getLevel() {
		return this.combatLevel;
	}

	@Override
	public String getTitle() {
		return this.title;
	}
	
	
}
