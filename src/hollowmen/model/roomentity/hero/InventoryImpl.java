package hollowmen.model.roomentity.hero;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

import hollowmen.model.Inventory;
import hollowmen.model.Item;
import hollowmen.model.Item.ItemState;
import hollowmen.model.item.ItemPool;
import hollowmen.model.utils.Cloner;
import hollowmen.model.utils.Counter;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

public class InventoryImpl implements Inventory{

	private Map<String, Pair<Item, Counter>> storage;

	
	public InventoryImpl() {
		this.storage = new HashMap<>();
	}
	
	public InventoryImpl(Collection<Pair<String, Integer>> itemInInventory) {
		this();
		itemInInventory.stream().forEach(e -> {
			Item i = ItemPool.getInstance().getItem(e.getX());
			i.setState(ItemState.UNEQUIPPED);
			this.storage.put(e.getX(), new Pair<>(i, new Counter(e.getY())));
		});
	}
	
	@Override
	public void addItem(Item item) {
		getOrCreateValue(item).getY().increase();
	}

	@Override
	public void removeItem(Item item) throws IllegalArgumentException{
		ExceptionThrower.checkIllegalArgument(storage, m -> !(m.containsKey(item.getInfo().getName()) 
															|| m.get(item.getInfo().getName()).getY().getCount() <= 0));
		this.storage.get(item.getInfo().getName()).getY().decrease();
	}

	@Override
	public Collection<Pair<Item, Integer>> getAllItem() {
		Collection<Pair<Item, Integer>> coll = new LinkedList<>();
		this.storage.entrySet().stream()
				.filter(e -> e.getValue().getY().getCount() > 0)
				.map(e -> e.getValue())
				.forEach(e -> coll.add(new Pair<>(e.getX(), e.getY().getCount())));
		return coll;
	}

	@Override
	public Collection<Item> getAllItemFound() {
		return this.storage.entrySet().stream()
				.map(e -> e.getValue().getX())
				.collect(Collectors.toList());
	}
		
	private Pair<Item, Counter> getOrCreateValue(Item item) {
		return storage.merge(item.getInfo().getName(), 
				new Pair<>(ItemPool.getInstance().getItem(item.getInfo().getName()), new Counter()), (x, y) -> x);
	}

	@Override
	public Item getItem(String itemName) throws IllegalArgumentException {
		ExceptionThrower.checkIllegalArgument(itemName, i -> !this.storage.containsKey(i));
		return Cloner.item(this.storage.get(itemName).getX());
		
	}
}
