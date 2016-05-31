package hollowmen.model.roomentity;

import hollowmen.model.Actor;

public class DumbMovePattern implements MovePattern{

	private EnemyAbs enemy;
	private String direction;
	
	public DumbMovePattern(EnemyAbs en) {
		this.enemy = en;
	}

	@Override
	public String getDirection() {
		if(enemy.isHittingWall()) {
			if(direction.equals(Actor.Direction.RIGHT.toString()))
				direction = Actor.Direction.LEFT.toString();
			else {
				direction = Actor.Direction.RIGHT.toString();
			}
		}
		return direction;
	}
	
	
}
