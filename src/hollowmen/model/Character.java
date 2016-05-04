package hollowmen.model;

import java.util.Collection;
/**
 * This interface represents the Character as an Object that can:<br>
 * -be moved in two {@link Direction},<br>
 * -perform action (as jump, attack...),<br>
 * <br>
 * A Character has some {@link Parameter}
 * @author pigio
 *
 */
public interface Character extends RoomEntity{

	/**
	 * RIGHT, LEFT.
	 * @author pigio
	 *
	 */
	public enum Direction{
		RIGHT,
		LEFT;
	}
	
	/**
	 * This method move the Character in the specified {@link Direction}.
	 * @param d {@link Direction} where the Character will move.
	 * @throws NullPointerException
	 */
	public void move(Direction d) throws NullPointerException;
	
	//state pattern per le TypeAction
	/**
	 * This method make the {@code Character} do something specified by action.
	 * @param action {@link TypeAction}
	 * @throws NullPointerException
	 */
	public void performAction(TypeAction action) throws NullPointerException;
	
	/**
	 * This method give all the {@link Parameter} of the Character
	 * @return {@link Collection}<{@link Parameter}> associated to the Character
	 */
	public Collection<Parameter> getParameters();
	
	/**
	 * 
	 * @return {@code true} if this {@code Character} is facing right, {@code false} if facing left
	 */
	public boolean isFacingRight();
}
