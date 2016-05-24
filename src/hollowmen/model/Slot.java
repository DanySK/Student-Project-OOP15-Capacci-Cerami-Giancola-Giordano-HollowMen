package hollowmen.model;

import java.util.Optional;

/**
 * This interface represents the Slot the {@link Hero} use for equip the {@link Item}
 * @author pigio
 *
 */
public interface Slot extends InformationUser{

	/**
	 * This method gives the {@code Item} equipped on this {@code Slot}, if present
	 * @return {@link Optional}<{@link Item}>
	 */
	public Optional<Item> getEquippedItem();
	
	/**
	 * This method sets the equipped {@code Item} with <b>item</b>
	 * @param item {@link Item} to sets on this {@code Slot}
	 */
	public void setItem(Optional<Item> item);
	
}
