package hollowmen.model.dungeon;

import java.util.Collection;
import java.util.LinkedList;

import hollowmen.model.Enemy;
import hollowmen.model.Parameter;
import hollowmen.model.Pokedex;
import hollowmen.model.Room;
import hollowmen.model.enemy.EnemyPool;
import hollowmen.utilities.Tris;

/**
 * This class implements {@link Pokedex}
 * @author pigio
 *
 */
public class PokedexImpl implements Pokedex{

	private Collection<Enemy> enemyMet;
	
	/**
	 * This constructor creates a new empty {@code Pokedex}
	 */
	public PokedexImpl() {
		enemyMet = new LinkedList<>();
	}

	/**
	 * This constructor creates a {@code Pokedex} using the <b>enemyName</b>
	 * to determine the enemies already met
	 * @param enemyName
	 */
	public PokedexImpl(Collection<Tris<String, Integer, String>> enemyName) {
		super();
		enemyName.stream()
			.forEach(e -> enemyMet.add(this.clearHP(EnemyPool.getInstance().getSpecificEnemy(e.getX(), e.getY(), e.getZ()))));
	}
	
	/**
	 * {@inheritDoc Pokedex}
	 */
	@Override
	public void checkNewEnemy(Room r) {
		r.getEnemies().stream()
			.filter(e -> !this.enemyMet.contains(e))
			.forEach(e -> this.enemyMet.add(e));
	}

	/**
	 * {@inheritDoc Pokedex}
	 */
	@Override
	public Collection<Enemy> getEnemyMet() {
		return this.enemyMet;
	}
	
	private Enemy clearHP(Enemy e) {
		e.getParameters().remove(Parameter.ParamName.HP.toString());
		return e;
	}
	
	
}
