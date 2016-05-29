package hollowmen.model.utils;

import java.util.stream.Collectors;

import hollowmen.model.Enemy;
import hollowmen.model.Item;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.enemy.EnemyFactory;
import hollowmen.model.item.ItemImpl;

public class Cloner {

	
	public static Item item(Item itemToClone) {
		return ItemImpl.builder().info(new InfoImpl(itemToClone.getInfo()))
				.state(itemToClone.getState())
				.modifier(itemToClone.getModifiers().entries().stream()
						.map(e -> e.getValue())
						.collect(Collectors.toList()))
				.value(itemToClone.getGoldValue())
				.rarity(itemToClone.getRarity())
				.slot(itemToClone.getSlot())
				.heroClass(itemToClone.getHeroClassEquippable())
				.build();
	}
	
	
	public static Enemy enemy(Enemy enemyToClone) {
		return EnemyFactory.getInstance().getBuilderFor(enemyToClone.getInfo().getName())
				.description(enemyToClone.getInfo().getDescription().get())
				.level(enemyToClone.getLevel())
				.param(enemyToClone.getParameters().entrySet().stream()
						.map(e -> e.getValue())
						.collect(Collectors.toList()))
				.title(enemyToClone.getTitle())
				.build();
	}
	
}
