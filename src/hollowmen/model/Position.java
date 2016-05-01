package hollowmen.model;

/**
 * This interface represent the 2D position of a {@link RoomEntity}
 * @author pigio
 *
 */
public interface Position {

	/**
	 * This method give the horizontal position value
	 * @return {@code double}
	 */
	public double getX();
	
	/**
	 * This method give the vertical position value
	 * @return {@code double}
	 */
	public double getY();
	
}
