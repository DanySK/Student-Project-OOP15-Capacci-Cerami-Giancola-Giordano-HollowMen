package hollowmen.model;

import org.jbox2d.dynamics.Body;

/**
 * This interface represents any actor in the {@link Room}, it has a {@link Body} and is an {@link InformationUser}
 * @author pigio
 *
 */
public interface RoomEntity extends InformationUser{
	
	/**
	 * This method give the {@code Size} of this {@code Body}
	 * @return {@link Body}
	 */
	public Body getBody();
	
	/**
	 * This method guarantee the right remove of this RoomEntity from the game
	 */
	public void dispose();
}
