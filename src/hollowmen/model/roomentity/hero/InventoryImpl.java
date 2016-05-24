package hollowmen.model.roomentity.hero;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import hollowmen.model.Inventory;
import hollowmen.model.Item;
import hollowmen.model.item.ItemPool;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

public class InventoryImpl implements Inventory{

	private Map<String, Pair<Item, Integer>> storage = new HashMap<>();

	
	@Override
	public void addItem(Item item) {
		Pair<Item, Integer> temp = getOrCreateValue(item);
		storage.put(item.getInfo().getName(), 
				new Pair<>(temp.getX(), temp.getY()+1));
	}

	@Override
	public void removeItem(Item item) throws IllegalArgumentException{
		ExceptionThrower.checkIllegalArgument(storage, m -> m.containsKey(item.getInfo().getName()) 
															&& m.get(item.getInfo().getName()).getY() > 0);
		
		Pair<Item, Integer> temp = storage.get(item.getInfo().getName());
		storage.put(item.getInfo().getName(),
				new Pair<>(temp.getX(), temp.getY()-1));
	}

	@Override
	public Collection<Pair<Item, Integer>> getAllItem() {
		return this.storage.entrySet().stream()
				.filter(e -> e.getValue().getY() > 0)
				.map(e -> e.getValue())
				.collect(Collectors.toList());
	}

	@Override
	public Collection<Item> getAllItemFound() {
		return this.storage.entrySet().stream()
				.map(e -> e.getValue().getX())
				.collect(Collectors.toList());
	}
		
	private Pair<Item, Integer> getOrCreateValue(Item item) {
		return storage.merge(item.getInfo().getName(), 
				new Pair<>(ItemPool.getInstance().getItem(item.getInfo().getName()), 0), (x, y) -> x);
	}
}
