package hollowmen.model;

/**
 * This interface represents an object that can be looted after
 * meet some condition
 * @author pigio
 *
 */
public interface Achievement extends InformationUser{

	/**
	 * This method check if the conditions for looting are met, if so the
	 * {@code Challenge} become lootable
	 */
	public void check();
	
	/**
	 * This method gives a {@code LimitedValue} which value is the progress and
	 * the limit is when this {@code Achievement} can be looted
	 * @return {@link LimitedValue}
	 */
	public LimitedValue<Integer> getProgress();
	
	/**
	 * 
	 * @return {@link Lootable} reward
	 * @throws IllegalStateException If {@code isLootable} return false
	 */
	public Lootable getLoot() throws IllegalStateException;
}
