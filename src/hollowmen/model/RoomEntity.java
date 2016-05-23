package hollowmen.model;

import java.awt.Rectangle;

/**
 * This interface represents any actor in the {@link Room}, it has a {@link Rectangular} and is an {@link InformationUser}
 * @author pigio
 *
 */
public interface RoomEntity extends InformationUser{
	
	/**
	 * This method give the {@code Size} of this {@code RoomEntity}
	 * @return {@link Rectangle}
	 */
	public Rectangle getSize();
	
}
