package hollowmen.model.utils;

import java.util.Collection;
import java.util.LinkedList;

import hollowmen.enumerators.EnemyTitle;
import hollowmen.enumerators.ParamName;
import hollowmen.model.Actor;
import hollowmen.model.Enemy;
import hollowmen.model.Lootable;
import hollowmen.model.Modifier;
import hollowmen.model.dungeon.DungeonSingleton;
import hollowmen.model.dungeon.LootableImpl;
import hollowmen.model.dungeon.ModifierImpl;
import hollowmen.model.enemy.EnemyPool;
import hollowmen.model.item.ItemPool;
import hollowmen.utilities.RandomSelector;

public class Algorithms {

	private final static int MIN_DIGIT = 100;
	private final static double ITEM_CHANCE_ORDINARY = 0.1 * MIN_DIGIT; 
	private final static double ITEM_CHANCE_COMMANDER = 0.3 * MIN_DIGIT; 
	private final static double ITEM_CHANCE_BOSS = 1 * MIN_DIGIT; 
	private final static double FLAT_ENEMY_EXP = 100;
	private final static double FLAT_ENEMY_GOLD = 75;
	
	
	
	public static Collection<Enemy> generateEnemy() {
		int maxLevel = (DungeonSingleton.getInstance().getFloorNumber() / 2) 
				+ DungeonSingleton.getInstance().getCurrentRoom().getRoomNumber() / Constants.ROOM_TO_VISIT;
		int maxPower = DungeonSingleton.getInstance().getCurrentRoom().getRoomNumber();
		Collection<Enemy> retValue = new LinkedList<>();
		while(maxPower > 0) {
			Enemy e = EnemyPool.getInstance().getRandomForLevelTitle(p -> p <= maxLevel, s -> s.equals(EnemyTitle.ORDINARY));
			maxPower -= e.getLevel();
			retValue.add(e);
		}
		return retValue;
	}
	
	public static Lootable genLootEnemy(Enemy en) {
		double titleMul = 1;
		double titleRarity = 0;
		double itemChance = 0;
		EnemyTitle title = EnemyTitle.valueOf(en.getTitle());
		
		switch(title) {
		case ORDINARY:
			titleMul = 1;
			titleRarity = 0;
			itemChance = Algorithms.ITEM_CHANCE_ORDINARY;
			break;
		case COMMANDER:
			titleMul = 1.5;
			titleRarity = 1;
			itemChance = Algorithms.ITEM_CHANCE_COMMANDER;
			break;
		case BOSS:
			titleMul = 2;
			titleRarity = 3;
			itemChance = Algorithms.ITEM_CHANCE_BOSS;
			break;
		}
		int gold = (int) (Algorithms.FLAT_ENEMY_GOLD * titleMul);
		int exp = (int) (Algorithms.FLAT_ENEMY_EXP * titleMul);
		
		if(RandomSelector.getIntFromRange(0, MIN_DIGIT) > itemChance) {
			final int rarity = (int) (en.getLevel() + titleRarity);
			return new LootableImpl(gold, exp, ItemPool.getInstance()
					.getRandom(x -> x.getRarity() >= en.getLevel() || x.getRarity() <= rarity));
		}
		return new LootableImpl(gold, exp, null);
	}
	
	public static void combat(Actor hitter, Actor subj) {
		double atkValue = hitter.getParameters().get(ParamName.ATTACK.toString()).getValue();
		double defValue = subj.getParameters().get(ParamName.DEFENSE.toString()).getValue();
		double res = ((atkValue - defValue) < 1) ? 1 : atkValue - defValue;
		subj.getParameters().get(ParamName.HP.toString())
			.addModifier(new ModifierImpl(ParamName.HP.toString(), -res, Modifier.Operation.ADD));
	}
	
}
