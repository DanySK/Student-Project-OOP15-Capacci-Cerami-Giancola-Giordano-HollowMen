package hollowmen.model.dungeon;

import java.util.Optional;

import hollowmen.model.Item;
import hollowmen.model.Lootable;


public class LootableImpl implements Lootable{

	private int gold;
	
		private int exp;
	
		private Optional<Item> item;
		
		
		public LootableImpl(int gold, int exp, Item item){
		this.gold = gold;
		this.exp = exp;
		this.item = Optional.ofNullable(item);
		};
		
		@Override
		public int getGold() {
			return this.gold;
		}

		@Override
		public int getExp() {
			return this.exp;
		}

		@Override
		public Optional<Item> getItem() {
			return this.item;
		}
		
}
