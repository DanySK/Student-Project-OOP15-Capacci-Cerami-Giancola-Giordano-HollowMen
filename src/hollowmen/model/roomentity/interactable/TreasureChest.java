package hollowmen.model.roomentity.interactable;

import hollowmen.model.Lootable;
import hollowmen.model.dungeon.FloorSingleton;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.dungeon.LootableImpl;
import hollowmen.model.roomentity.UselessInteractable;
import hollowmen.model.utils.Constants;

public class TreasureChest extends UselessInteractable{

	
	private Lootable loot;
	
	
	public TreasureChest(int rarity) {
		super(new InfoImpl("treasure"), Constants.TREASURESIZE);
		this.loot = LootableImpl.create(20*rarity, 50*rarity, 5*rarity);
	}

	
	@Override
	public void interact() throws IllegalStateException {
		super.interact();
		FloorSingleton.getInstance().getCurrentRoom().getHero().pick(loot);
	}

}
