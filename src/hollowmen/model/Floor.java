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
	 * This method give the {@code Floor} number, it's number influence the {@link Enemy} found in each {@link Room}
	 * @return {@code int} that indicates the {@code Floor} number
	 */
	public int getFloorNumber();
	
	/**
	 * This method give the current {@link Room} visited by the {@link Hero}, basically the {@code Room} where the player is
	 * @return {@link Room} where the player is
	 */
	public Room getCurrentRoom();
	
}
