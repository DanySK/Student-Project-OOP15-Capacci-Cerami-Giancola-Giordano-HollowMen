package hollowmen.model.utils;

import java.util.stream.Collectors;

import hollowmen.model.Item;
import hollowmen.model.dungeon.InfoImpl;
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
	
}
