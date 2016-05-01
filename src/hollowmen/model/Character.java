package hollowmen.model;

import java.util.Collection;
/**
 * This interface represents the Character as an Object that can:<br>
 * -be moved in two {@link Direction},<br>
 * -perform action (as jump, attack...),<br>
 * <br>
 * A Character has some {@link Parameter} that be modified due {@link Modifier}
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
	 * This method make the Character do something specified by action.
	 * @param action {@link TypeAction}
	 * @throws NullPointerException
	 */
	public void performAction(TypeAction action) throws NullPointerException;
	
	/**
	 * This method <u>add</u> a {@link Modifier} to the Character
	 * @param mod {@link Modifier}
	 */
	public void addModifier(Modifier mod) throws NullPointerException;
	
	/**
	 * This method <u>remove</u> a {@link Modifier} to the Character
	 * @param mod {@link Modifier}
	 * @throws IllegalArgumentException If the Character hasn't the passed {@link Modifier}
	 * @throws NullPointerException
	 */
	public void removeModifier(Modifier mod) throws IllegalArgumentException, NullPointerException;
	
	/**
	 * This method give all the {@link Parameter} of the Character
	 * @return {@link Collection}<{@link Parameter}> associated to the Character
	 */
	public Collection<Parameter> getParameters();
}
