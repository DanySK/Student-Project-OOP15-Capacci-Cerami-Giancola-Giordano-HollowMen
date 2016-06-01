package hollowmen.model.dungeon;

import java.util.Collection;
import java.util.Collections;

import hollowmen.model.Attack;
import hollowmen.model.Enemy;
import hollowmen.model.Interactable;
import hollowmen.model.Room;
import hollowmen.model.RoomEntity;
import hollowmen.utilities.ExceptionThrower;

public class Lobby implements Room{
	
	public Lobby() {}
	
	@Override
	public Room getParentRoom() throws IllegalStateException{
		ExceptionThrower.checkIllegalState(this, x -> true);
		return null;
	}

	@Override
	public Room getChildRoom(int choice) throws IllegalArgumentException {
		ExceptionThrower.checkIllegalState(this, x -> true);
		return null;
	}

	@Override
	public Collection<RoomEntity> getAllEntities() {
		return Collections.emptyList();
	}

	@Override
	public Collection<Enemy> getEnemies() {
		return Collections.emptyList();
	}

	@Override
	public Collection<Attack> getBullets() {
		return Collections.emptyList();
	}

	@Override
	public Collection<Interactable> getInteractable() {
		return Collections.emptyList();
	}

	@Override
	public int getRoomNumber() {
		return 0;
	}

	@Override
	public void addEntity(RoomEntity roomEntity) throws IllegalArgumentException {}

	@Override
	public void removeEntity(RoomEntity roomEntity) throws IllegalArgumentException {}

	@Override
	public void autoPopulate() {}

}
