package hollowmen.model.roomentity.hero;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import hollowmen.model.Inventory;
import hollowmen.model.Item;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

public class InventoryImpl implements Inventory{

	private Map<String, List<Item>> storage = new HashMap<>();

	
	@Override
	public void addItem(Item item) {
		this.getAssicuratedValue(item).add(item);
	}

	@Override
	public void removeItem(Item item) throws IllegalArgumentException{
		ExceptionThrower.checkIllegalArgument(storage, m -> m.containsKey(item.getInfo().getName()));
		this.storage.get(item.getInfo().getName()).remove(item);
	}

	@Override
	public Collection<Pair<Item, Integer>> getAllItem() {
		return this.storage.entrySet().stream()
				.filter(e -> !e.getValue().isEmpty())
				.map(e -> new Pair<Item, Integer>(e.getValue().get(0), e.getValue().size()))
				.collect(Collectors.toList());
	}

	@Override
	public Collection<Item> getAllItemFound() {
		return this.storage.entrySet().stream()
				.map(e -> e.getValue().get(0))
				.collect(Collectors.toList());
	}
	
	private List<Item> getAssicuratedValue(Item item) {
		return storage.merge(item.getInfo().getName(), new LinkedList<>(), (x, y) -> x);
	}
	
	
	
}
