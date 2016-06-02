package hollowmen.model.roomentity;

import java.util.Collection;

import org.jbox2d.dynamics.Filter;

import hollowmen.model.Enemy;
import hollowmen.model.Information;
import hollowmen.model.Lootable;
import hollowmen.model.Parameter;
import hollowmen.model.dungeon.FilterType;
import hollowmen.model.utils.Algorithms;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.utilities.Pair;

public abstract class EnemyAbs extends ActorAbs implements Enemy{

	protected MovePattern pattern;
	private int combatLevel;
	private String title;
	private Lootable loot;
	private boolean hitWall;
	
	public EnemyAbs(Information info, Pair<Float, Float> size, Collection<Parameter> param, int power, String title) {
		super(info, size, param);
		this.combatLevel = power;
		this.title = title;
	}
	
	public MovePattern movePattern() {
		return new DumbMovePattern(this);
	}

	@Override
	public void move(String s) {
		super.move(pattern == null ? movePattern().getDirection() : pattern.getDirection());
	}

	@Override
	public Lootable getLoot() {
		return this.loot;
	}
	
	@Override
	public int getLevel() {
		return this.combatLevel;
	}

	@Override
	public String getTitle() {
		return this.title;
	}
	
	public Filter standardEnemyFilter() {
		return Box2DUtils.filterBuilder()
				.addCategory(FilterType.ENEMY.getValue())
				.addMask(FilterType.GROUND.getValue())
				.addMask(FilterType.HERO.getValue())
				.addMask(FilterType.HEROATTACK.getValue())
				.build();
	}
	
	public void autoGenLoot() {
		this.loot = Algorithms.genLootEnemy(this);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + combatLevel;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		super.equals(obj);
		if (getClass() != obj.getClass())
			return false;
		EnemyAbs other = (EnemyAbs) obj;
		if (combatLevel != other.combatLevel)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

	public boolean isHittingWall() {
		return hitWall;
	}

	public void setHitWall(boolean hitWall) {
		this.hitWall = hitWall;
	}
	
}
