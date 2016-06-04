package hollowmen.model.dungeon;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import hollowmen.model.Item;
import hollowmen.model.Item.ItemState;
import hollowmen.model.Shop;
import hollowmen.model.item.ItemPool;

public class ShopImpl implements Shop{

	
	private Collection<Item> sellableItem = new LinkedList<Item>();
	
	public ShopImpl(Collection<String> allItemName) {
		allItemName.stream()
			.forEach(x -> {
				Item temp = ItemPool.getInstance().getItem(x);
				temp.setState(ItemState.BUYABLE);
				sellableItem.add(temp);
			});
	}
	
	public ShopImpl(Collection<Item> shop, int i) {
		shop.stream().forEach(x -> x.setState(ItemState.BUYABLE));
		this.sellableItem = shop;
	}

	@Override
	public Collection<Item> getShopItem() {
		return Collections.unmodifiableCollection(this.sellableItem);
	}

}
