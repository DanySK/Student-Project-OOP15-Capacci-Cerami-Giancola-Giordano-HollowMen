package hollowmen.model.enemy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import hollowmen.model.Enemy;
import hollowmen.model.utils.Cloner;
import hollowmen.utilities.RandomSelector;
import hollowmen.utilities.Tris;

public class EnemyPool {

	
	private Map<Tris<String, Integer, String>, Enemy> pool;
	
	private EnemyPool(){
		this.pool = new HashMap<>();
	};

	private static class Holder {
		public static EnemyPool INSTANCE = new EnemyPool();
	}

	/**
	 * 
	 * @return the unique instance for this class
	 */
	public static EnemyPool getInstance() {
		return Holder.INSTANCE;
	}
	
	public void addEnemy(Enemy enemy) {
		enemy.getBody().setActive(false);
		pool.put(new Tris<>(enemy.getInfo().getName(), enemy.getLevel(), enemy.getTitle()), enemy);
	}
	
	public Enemy getSpecificEnemy(String name, int level, String title) {
		return Cloner.enemy(this.pool.get(new Tris<>(name, level, title)));
	}
	
	
	public Enemy getCompletelyRandom() {
		return Cloner.enemy((Enemy) RandomSelector.getAnyFrom(pool.entrySet().stream().map(e -> e.getValue()).toArray()));
	}
	
	public Enemy getRandomForName(Predicate<String> func) {
		return Cloner.enemy((Enemy) RandomSelector.getAnyFrom(this.pool.entrySet().stream()
					.filter(e -> func.test(e.getKey().getX()))
					.map(e -> e.getValue())
					.toArray()));
	}
	
	public Enemy getRandomForLevel(Predicate<Integer> func) {
		return Cloner.enemy((Enemy) RandomSelector.getAnyFrom(this.pool.entrySet().stream()
				.filter(e -> func.test(e.getKey().getY()))
				.map(e -> e.getValue())
				.toArray()));
	}
	
	public Enemy getRandomForTitle(Predicate<String> func) {
		return Cloner.enemy((Enemy) RandomSelector.getAnyFrom(this.pool.entrySet().stream()
					.filter(e -> func.test(e.getKey().getZ()))
					.map(e -> e.getValue())
					.toArray()));
	}
	
	public Enemy getRandomForLevelTitle(Predicate<Integer> funcInt, Predicate<String> funcTit) {
		Enemy en = Cloner.enemy((Enemy) RandomSelector.getAnyFrom(this.pool.entrySet().stream()
				.filter(e -> funcInt.test(e.getKey().getY()))
				.filter(e -> funcTit.test(e.getKey().getZ()))
				.map(e -> e.getValue())
				.toArray()));
		System.out.println(en);
		return en;
	}
	
}