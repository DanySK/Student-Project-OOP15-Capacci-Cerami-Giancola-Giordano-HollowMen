package hollowmen.model;

/**
 * This interface represents an object that can be looted after
 * meet some condition
 * @author pigio
 *
 */
public interface Achievement{

	/**
	 * This method check if the conditions for looting are met, if so the
	 * {@code Challenge} become lootable
	 */
	public void check();
	
	/**
	 * @return {@code true} if this can be looted, {@code false} otherwise
	 */
	public boolean isLootable();
	
	/**
	 * 
	 * @return {@link Lootable} reward
	 * @throws IllegalStateException If {@code isLootable} return false
	 */
	public Lootable getLoot() throws IllegalStateException;
}
