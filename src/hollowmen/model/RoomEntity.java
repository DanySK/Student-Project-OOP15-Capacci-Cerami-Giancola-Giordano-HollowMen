package hollowmen.model;

/**
 * This interface combine a {@link Position} and {@link Information}
 * @author pigio
 *
 */
public interface RoomEntity extends Information{
	
	/**
	 * This method give the {@code Size} of this {@code RoomEntity}
	 * @return {@link Size}
	 */
	public Size getSize();
	
}
