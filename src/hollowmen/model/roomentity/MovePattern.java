package hollowmen.model.roomentity;

import hollowmen.model.Actor.Direction;

/**
 * A simple strategy for let the Enemy choose where move
 * @author pigio
 *
 */
public interface MovePattern {

	public Direction getDirection();
	
}
