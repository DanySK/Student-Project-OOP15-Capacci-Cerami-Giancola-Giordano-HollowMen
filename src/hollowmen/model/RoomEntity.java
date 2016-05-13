package hollowmen.model;

/**
 * This interface represents any actor in the {@link Room}, it has a {@link Size} and is an {@link InformationUser}
 * @author pigio
 *
 */
public interface RoomEntity extends InformationUser{
	
	/**
	 * This method give the {@code Size} of this {@code RoomEntity}
	 * @return {@link Size}
	 */
	public Size getSize();
	
	/**
	 * This method give the {@code int} assigned by the {@code Room} to this {@code RoomEntity}
	 * This number is univocal inside the current {@code Room}
	 * @return {@code int} represents the ID inside the {@link Room}
	 */
	public int getID();
	
	/**
	 * This method allow RoomEntity to be stored in HashMap
	 * @return
	 */
	public int hashCode();
}
