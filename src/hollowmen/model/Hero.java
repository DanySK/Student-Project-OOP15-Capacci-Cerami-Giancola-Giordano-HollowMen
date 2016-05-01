package hollowmen.model;

/**
 * This interface represents the {@code Hero} intended as the {@link Character} controlled by the player<br>
 * <br>
 * The {@code Hero} can equip, unequip, sell and buy {@link Item}<br>
 * <br>
 * The {@code Hero} has a {@link Level} that increase his {@link Parameter} and add skill points to the {@link SkillTree}<br>
 * <br>
 * The {@code Hero} has an {@link Inventory} to keep all {@link Item} found during the game<br>
 * <br>
 * The {@code Hero} has a {@link Pokedex} to keep all information about the {@link Enemy} met<br>
 * <br>
 * The {@code Hero} has a {@link HeroClass} that hold the information about his {@link SkillTree},
 * his basic attack range and {@link Parameter}<br>
 * <br>
 * @author pigio
 *
 */
public interface Hero extends Character{

	/**
	 * This method equip the <b>item</b> adding it's {@link Modifier} to the {@code Hero}'s {@link Parameter} <br>
	 * <br>
	 * the <b>item</b> will replace the {@link Item} already equipped in the same slot
	 * 
	 * @param item {@link Item} to equip
	 * @throws IllegalStateException If the <b>item</b> can't be equipped
	 * @throws NullPointerException
	 */
	public void equipItem(Item item) throws IllegalStateException, NullPointerException;
	
	/**
	 * This method unequip the <b>item</b> removing it's {@link Modifier} to the {@code Hero}'s {@link Parameter} <br>
	 * 
	 * @param item {@link Item} to unequip
	 * @throws IllegalStateException if the <b>item</b> isn't equipped
	 * @throws NullPointerException
	 */
	public void unequipItem(Item item) throws IllegalStateException, NullPointerException;

	/**
	 * This method sell the passed <b>item</b> removing it from the {@link Inventory}
	 * and add his sellValue to the {@code Hero}'s gold
	 * @param item {@link Item} to sell
	 * @throws IllegalStateException If the <b>item</b> is unsellable
	 * @throws IllegalArgumentException If the <b>item</b> isn't in the {@code Inventory}
	 * @throws NullPointerException
	 */
	public void sellItem(Item item) throws IllegalStateException, IllegalArgumentException, NullPointerException;
	
	/**
	 * This method buy an <b>item</b> adding it to the {@link Inventory} and subtract his sellValue*2 to the Hero's gold
	 * @param item the {@link Item} to sell
	 * @throws IllegalStateException If the {@code Hero} has less gold than the <b>item</b>'s sellValue*2
	 * @throws NullPointerException
	 */
	public boolean buyItem(Item item) throws IllegalStateException, NullPointerException;
	
	/**
	 * This method give the {@code Hero}'s {@code Level}
	 * 
	 * @return {@link Level}
	 */
	public Level getLevel();
	
	/**
	 * This method give the {@code Hero}'s gold
	 * @return {@code int} that represents the Hero's gold
	 */
	public int getGold();
	
	/**
	 * This method give the {@code Hero}'s {@code Inventory}
	 * @return {@link Inventory}
	 */
	public Inventory getInventory();
	
	/**
	 * This method give the {@code Hero}'s {@code Pokedex}
	 * @return {@link Pokedex}
	 */
	public Pokedex getPokedex();
	
	/**
	 * This method give the {@code Hero}'s {@code HeroClass}
	 * @return {@link HeroClass}
	 */
	public HeroClass getHeroClass();
	
	/**
	 * This method allow the {@code Hero} to add Exp, Gold and {@code Item} using the passed {@link Lootable} object
	 * @param loot {@link Lootable}
	 * @throws NullPointerException
	 */
	public void pick(Lootable loot) throws NullPointerException;
	
	/**
	 * This method add a {@link Modifier} on the {@code Hero}'s <b>param</b><br>
	 * @param param {@link Parameter} to add a stat point
	 * @throws IllegalArgumentException If the Hero hasn't the passed {@link Parameter}
	 * @throws NullPointerException
	 */
	public void spendStatPointOn(Parameter param) throws IllegalArgumentException, NullPointerException;
	
	/**
	 * This method give the Hero's unspent stat point
	 * @return {@code int} that represents the Hero's unspent stat point
	 */
	public int getUnspentStatPoint();
}
