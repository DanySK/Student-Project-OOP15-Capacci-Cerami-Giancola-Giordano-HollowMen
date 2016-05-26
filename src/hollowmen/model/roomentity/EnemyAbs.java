package hollowmen.model.roomentity;

import java.awt.Rectangle;
import java.util.Collection;

import hollowmen.model.Enemy;
import hollowmen.model.Information;
import hollowmen.model.Lootable;
import hollowmen.model.Parameter;
import hollowmen.model.dungeon.LootableCreatorSingleton;

public abstract class EnemyAbs extends ActorAbs implements Enemy{

	
	private MovePattern pattern;
	private int combatPower;
	private Lootable loot = LootableCreatorSingleton.createFromEnemy(100, 100, 0.15);
	
	private EnemyAbs(Information info, Rectangle size, ActionAllowed aA, Collection<Parameter> param) {
		super(info, size, aA, param);
		// TODO Auto-generated constructor stub
	}

	public EnemyAbs(Information info, Rectangle size, ActionAllowed aA, 
			Collection<Parameter> param, MovePattern movP, int power) {
		this(info, size, aA, param);
		this.pattern = movP;
		this.combatPower = power;
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
		return this.combatPower;
	}

	@Override
	public Lootable getLoot() {
		return loot;
	}

	
	
}
