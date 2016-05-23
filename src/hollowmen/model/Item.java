package hollowmen.model;

import java.util.Collection;

/**
 * This interface represent {@code Item} intended as an Object that has:<br>
 * -A defined {@link ItemState}<br>
 * -Some {@link Modifier}<br>
 * -Has a sellValue and a rarity {@code int}<br>
 * -Has a target slot and a target hero class<br>
 * @author pigio
 *
 */
public interface Item extends InformationUser{

	/**
	 * This {@link Enum} represent all the possible {@code ItemState}<br>
	 * <br>
	 * EQUIPPED, UNEQUIPPED, SELLABLE, UNSELLABLE, BUYABLE;
	 * @author pigio
	 *
	 */
	public enum ItemState{
		EQUIPPED,
		UNEQUIPPED,
		SELLABLE,
		UNSELLABLE,
		BUYABLE;
	}
	
	/**
	 * This method give the {@code ItemState}
	 * @return {@link ItemState} 
	 */
	public ItemState getState();
	
	/**
	 * This method set the state of this {@code Item} with <b>state</b>
	 */
	public void setState(ItemState state);
	
	/**
	 * This method give all the {@code Item}'s{@code Modifier}
	 * @return {@link Collection}<{@link Modifier}>
	 */
	public Collection<Modifier> getModifiers();
	
	/**
	 * This method give the {@code Item}'s sellValue
	 * @return {@code int} represents the sellValue
	 */
	public int getSellValue();
	
	/**
	 * This method give the {@code Item}'s rarity
	 * @return {@code int} represents the rarity
	 */
	public int getRarity();
	
	/**
	 * This method give a {@code Information} represent where this {@code Item} can be equipped
	 * @return {@code String} represent where this {@code Item} can be equipped
	 */
	public String getSlot();
	
	/**
	 * This method give a {@code Information} represent on which {@code HeroClass} this {@code Item} can be equipped
	 * @return {@code String} represent on which {@link HeroClass} this {@code Item} can be equipped
	 */
	public String getHeroClassEquippable();
	
	
}
