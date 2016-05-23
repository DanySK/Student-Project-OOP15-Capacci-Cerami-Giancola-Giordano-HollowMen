package hollowmen.model;

/**
 * This interface represents all the {@code Enemy} intended as {@link Actor} that will fight the {@link Hero}
 * @author pigio
 *
 */
public interface Enemy extends Actor{
		
	/**
	 * This method gives an integer value that summarizes the {@code Enemy} power
	 * @return {@code int} for the power of this {@code Enemy}
	 */
	public int getCombatPower();

	/**
	 * This method gives the title of this {@code Enemy}<br>
	 * ex. "boss", "commander", "ordinary"
	 * @return {@code String}
	 */
	public String getTitle();
	
	/**
	 * This method gives the habitat of this {@code Enemy}<br>
	 * ex. "ground", "fly"
	 * @return
	 */
	public String getHabitat();
	
	/**
	 * This method gives the {@code Lootable} object for this {@code Enemy}
	 * @return {@link Lootable}
	 */
	public Lootable getLoot();
}
