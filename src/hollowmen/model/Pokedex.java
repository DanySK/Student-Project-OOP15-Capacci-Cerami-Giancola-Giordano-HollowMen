package hollowmen.model;

import java.util.Collection;

/**
 * This interface represents an object that tracks all the {@link Enemy} met
 * @author pigio
 *
 */
public interface Pokedex {

	/**
	 * This method give all the {@code Enemy} met
	 * @return {@link Collection}<{@link Enemy}>
	 */
	public Collection<Enemy> getEnemyMet();
	
}
