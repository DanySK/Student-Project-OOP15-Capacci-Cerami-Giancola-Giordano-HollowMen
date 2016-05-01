package hollowmen.model;

import java.util.Collection;
import java.util.Optional;

/**
 * This interface represent a {@code Room} intended as a container for {@link RoomEntity}<br>
 * {@code Room}s compose a single {@link Floor} and every {@code Room} has a "parent" {@code Room}
 * and not always "child" {@code Room}
 * 
 * @author pigio
 *
 */
public interface Room {

	/**
	 * This method give the {@code Room} "before" this
	 * @return {@link Room}
	 */
	public Room getParentRoom();
	
	/**
	 * This method give the number of "next" {@code Room} from this one
	 * @return {@code int}
	 */
	public int getChildRoomNumber();
	
	/**
	 * 
	 * This method give the selected child{@code Room}
	 * @param choice {@code int} counting from left to right
	 * @return {@link Room}
	 * @throws IllegalArgumentException If <b>choice</b> > childRoomNumber, or zero, or negative
	 */
	public Room getChildRoom(int choice) throws IllegalArgumentException;
	
	/**
	 * This method give all the {@code RoomEntity} in this {@code Room}
	 * @return {@link Collection}<{@link RoomEntity}>
	 */
	public Collection<RoomEntity> getAllEntities();
	
	/**
	 * This method give all the {@code Enemy} in this {@code Room}
	 * @return {@link Optional}<{@link Collection}<{@link Enemy}>>
	 */
	public Optional<Collection<Enemy>> getEnemies();
	
	/**
	 * This method give all the {@code Interactable} in this {@code Room}
	 * @return {@link Optional}<{@link Collection}<{@link Interactable}>>
	 */
	public Optional<Collection<Interactable>> getInteractable();
	
	/**
	 * This method give the number of this {@code Room} inside the {@code Floor}
	 * @return {@code int}
	 */
	public int getRoomNumber();
}
