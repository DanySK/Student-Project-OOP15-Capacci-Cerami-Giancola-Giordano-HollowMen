package hollowmen.model;

import java.util.Collection;

/**
 * This interface represents the {@code Inventory} intended as an object that
 * holds {@link Item}<br>
 * {@code Inventory} tracks all {@code Item} that has and has had
 * @author pigio
 *
 */
public interface Inventory {

	/**
	 * This method add the <b>item</b> to the {@code Inventory}
	 * @param item {@link Item} to add
	 */
	public void addItem(Item item);
	
	/**
	 * This method remove the <b>item</b> to the {@code Inventory}
	 * @param item {@link Item} to remove
	 */
	public void removeItem(Item item);
	
	/**
	 * This method give all the {@link Item} currently stored in the {@code Inventory}
	 * @return {@link Collection}<{@link Item}> currently stored in the {@code Inventory}
	 */
	public Collection<Item> getAllItem();
	
	/**
	 * This method give all the {@link Item} that has had stored in the {@code Inventory}
	 * @return {@link Collection}<{@link Item}> has had stored in the {@code Inventory}
	 */
	public Collection<Item> getAllItemFound();
	
}
