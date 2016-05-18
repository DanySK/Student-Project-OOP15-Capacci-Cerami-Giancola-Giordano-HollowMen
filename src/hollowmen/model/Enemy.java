package hollowmen.model;

/**
 * This interface represents all the {@code Enemy} intended as {@link Actor} that will fight the {@link Hero}
 * @author pigio
 *
 */
public interface Enemy extends Actor, Lootable{
		
	/**
	 * This method give an integer value that summarizes the {@code Enemy} power
	 * @return {@code int} for the power of this {@code Enemy}
	 */
	public int getCombatPower();

}
