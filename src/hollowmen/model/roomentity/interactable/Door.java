package hollowmen.model.roomentity.interactable;

import java.util.Collection;

import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Filter;
import org.jbox2d.dynamics.FixtureDef;

import hollowmen.model.collision.Utils;
import hollowmen.model.collision.hitbox.FilterType;
import hollowmen.model.dungeon.FloorSingleton;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.roomentity.UselessInteractable;
import hollowmen.model.utils.Constants;

public class Door extends UselessInteractable{

	private int roomNumber;
	
	public Door(String name, int doorNumber) {
		super(new InfoImpl(name));
		this.roomNumber = doorNumber;
	}
	
	@Override
	public boolean isInteractAllowed() {
		return FloorSingleton.getInstance().getCurrentRoom().getEnemies().isEmpty();
	}
	
	@Override
	public void interact() throws IllegalStateException {
		super.interact();
		FloorSingleton.getInstance().changeRoom(roomNumber);		
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	@Override
	public BodyDef defBody() {
		return Utils.bodyDefBuilder().fixRotation(true).type(BodyType.STATIC).build();
	}

	@Override
	public Collection<FixtureDef> defFixture() {
		Filter filter = Utils.filterBuilder()
							.addCategory(FilterType.LOOTABLE.getValue())
							.addMask(FilterType.GROUND.getValue())
							.addMask(FilterType.HERO.getValue())
							.build();
		return this.generateRectangleFix(Constants.DOOR_SIZE, filter, true);
	}

}
