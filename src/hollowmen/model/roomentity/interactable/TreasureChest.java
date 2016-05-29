package hollowmen.model.roomentity.interactable;

import java.util.Collection;

import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import hollowmen.enumerators.RoomEntityName;
import hollowmen.model.Lootable;
import hollowmen.model.dungeon.DungeonSingleton;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.dungeon.LootableImpl;
import hollowmen.model.item.ItemPool;
import hollowmen.model.roomentity.UselessInteractable;
import hollowmen.model.utils.Constants;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.utilities.RandomSelector;

public class TreasureChest extends UselessInteractable{
	
	private Lootable loot;
	
	//TODO improve the choose of the Item
	public TreasureChest(int rarity) {
		super(new InfoImpl(RoomEntityName.TREASURE.toString()), Constants.TREASURE_SIZE);
		int floorNum = DungeonSingleton.getInstance().getFloorNumber();
		int roomNum = DungeonSingleton.getInstance().getCurrentRoom().getRoomNumber();
		int expAndGold = floorNum * Constants.TREASURE_FLATFLOOR + roomNum * Constants.TREASURE_FLATROOM;
		if(RandomSelector.getIntFromRange(0, 100) < Constants.TREASURE_ITEMCHANCE) {
			this.loot = new LootableImpl(expAndGold, expAndGold, ItemPool.getInstance().getCompletelyRandom());
		} else {
			this.loot = new LootableImpl(expAndGold, expAndGold, null);
		}
	}

	@Override
	public void interact() throws IllegalStateException {
		super.interact();
		DungeonSingleton.getInstance().getHero().pick(loot);
		DungeonSingleton.getInstance().getCurrentRoom().removeEntity(this);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((loot == null) ? 0 : loot.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreasureChest other = (TreasureChest) obj;
		if (loot == null) {
			if (other.loot != null)
				return false;
		} else if (!loot.equals(other.loot))
			return false;
		return true;
	}

	@Override
	public BodyDef defBody() {
		return Box2DUtils.bodyDefBuilder().type(BodyType.DYNAMIC).build();
	}

	@Override
	public Collection<FixtureDef> defFixture() {
		return this.generateRectangleFix(this.standardFilter(), true);
	}

}
