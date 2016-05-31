package hollowmen.model.dungeon;

import java.util.Collection;
import java.util.LinkedList;

import hollowmen.model.Enemy;
import hollowmen.model.Pokedex;
import hollowmen.model.Room;
import hollowmen.model.enemy.EnemyPool;
import hollowmen.utilities.Tris;

public class PokedexImpl implements Pokedex{

	private Collection<Enemy> enemyMet;
	
	public PokedexImpl() {
		enemyMet = new LinkedList<>();
	}

	public PokedexImpl(Collection<Tris<String, Integer, String>> enemyName) {
		super();
		enemyName.stream()
			.forEach(e -> enemyMet.add(EnemyPool.getInstance().getSpecificEnemy(e.getX(), e.getY(), e.getZ())));
	}
	
	
	@Override
	public void checkNewEnemy(Room r) {
		r.getEnemies().stream()
			.filter(e -> this.enemyMet.contains(e))
			.forEach(e -> this.enemyMet.add(e));
	}

	@Override
	public Collection<Enemy> getEnemyMet() {
		return this.enemyMet;
	}
	
	
	
	
}
