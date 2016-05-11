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
	
}
