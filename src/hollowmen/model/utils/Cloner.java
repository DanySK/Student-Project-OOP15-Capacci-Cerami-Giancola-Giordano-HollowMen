package hollowmen.model.utils;

import java.util.LinkedList;

import hollowmen.model.Item;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.item.ItemImpl;

public class Cloner {

	
	public static Item item(Item itemToClone) {
		return ItemImpl.builder().info(new InfoImpl(itemToClone.getInfo()))
				.state(itemToClone.getState())
				.modifier(new LinkedList<>(itemToClone.getModifiers()))
				.value(itemToClone.getSellValue())
				.rarity(itemToClone.getRarity())
				.slot(itemToClone.getSlot())
				.heroClass(itemToClone.getHeroClassEquippable())
				.build();
	}
	
}
