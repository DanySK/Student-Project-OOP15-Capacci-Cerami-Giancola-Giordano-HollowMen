package hollowmen.model;

/**
 * This interface indicates the Size intended as a rectangular occupied by the {@link RoomEntity}
 * it's represented by 2 {@link Point2D} once for the TopLeft corner and the other for the DownRight one
 * @author pigio
 *
 */
public interface Size {
	
	/**
	 * This method give the TopLeft corner
	 * @return {@link Point2D} the TopLeft corner
	 */
	public Point2D getTopLeft();
	
	/**
	 * This method give the DownRight corner
	 * @return {@link Point2D} the DownRight corner
	 */
	public Point2D getDownRight();
	
	/**
	 * 
	 * @return {@code double} Height for this rectangular
	 */
	public default double getHeight() {
		return this.getTopLeft().getY()-this.getDownRight().getY();
	}
	/**
	 * 
	 * @return {@code double} Lenght for this rectangular
	 */
	public default double getLenght() {
		return this.getDownRight().getX()-this.getTopLeft().getX();
	}
}
