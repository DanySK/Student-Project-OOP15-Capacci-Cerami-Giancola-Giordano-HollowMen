package hollowmen.model;

/**
 * This interface represents a {@code Floor} in the {@link Dungeon} intended as a sequence of {@link Room}<br>
 * <br>
 * The {@code Floor} has a number represent the position inside the {@code Dungeon}<br>
 * <br>
 * A {@code Floor} is locked until the player complete the previous one<br>
 * <br>
 * The {@code Floor} can return the {@code Room} where the {@link Hero} is and can
 * move the {@code Hero} to a new {@code Room}
 * @author pigio
 *
 */
public interface Floor {
	
	/**
	 * This method give information about the {@code Floor} state
	 * @return {@code true} if is unlocked, {@code false} otherwise
	 */
	public boolean isUnlocked();
	
	/**
	 * This method give the {@code Floor} number, it's number influence the {@link Enemy} found in each {@link Room}
	 * @return {@code int} that indicates the {@code Floor} number
	 */
	public int getFloorNumber();
	
	/**
	 * This method give the current {@link Room} visited by the {@link Hero}, basically the {@code Room} where the player is
	 * @return {@link Room} where the player is
	 */
	public Room getCurrentRoom();
	
	/**
	 * This method change the current {@link Room} with the <b>newRoom</b>, this allow the player to move through the {@code Room}
	 * @param newRoom {@link Room} where the player will move
	 * @throws NullPointerException
	 */
	public void changeRoom(Room newRoom) throws NullPointerException;
}
