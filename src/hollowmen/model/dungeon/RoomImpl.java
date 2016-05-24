package hollowmen.model.dungeon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hollowmen.model.Interactable;
import hollowmen.model.Room;
import hollowmen.model.RoomEntity;
import hollowmen.model.roomentity.Door;
import hollowmen.model.utils.Constants;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.RandomSelector;
import hollowmen.model.Bullet;
import hollowmen.model.Enemy;
import hollowmen.model.Hero;

public class RoomImpl implements Room{
	
	private Room parentRoom;
	
	private int childNumber;
	
	private List<Room> childRoom = new ArrayList<>();
	
	private Collection<Interactable> interactables;
	
	private Collection<Enemy> enemies;
	
	private Collection<Bullet> bullets;
	
	private List<RoomEntity> list;
	
	private int roomNumber;
	
	//TODO migliorare l'intervallo dei numeri mettendo numeri interi che vanno da 0 a Constants...
	private final int roomNumberWithChild = RandomSelector.getFrom(new Integer[]{0, 1, 2}); 
	
	/**
	 * @param parentRoom {@link Room} that is this one's parents
	 * @param childNumber how many child this Room will have
	 * @param roomNumber the room number
	 */
	public RoomImpl(Room parentRoom, int childNumber, int roomNumber) {
		this.parentRoom = parentRoom;
		this.childNumber = childNumber;
		this.roomNumber = roomNumber;
		createDoor();
	};
	
	private void createDoor() {
		for(int i = 0; i < this.childNumber; i++) {
			this.interactables.add(new Door(i));
		}
		this.interactables.add(new Door(-1));
	}

	@Override
	public Room getParentRoom() {
		return this.parentRoom;
	}


	@Override
	public Room getChildRoom(int choice) throws IllegalArgumentException {
		ExceptionThrower.<Integer>checkIllegalArgument(choice, i -> i < 0 || i > this.childNumber);
		if(childRoom.get(choice) == null) {
			return generateRoom(choice);
		}
		return childRoom.get(choice);
	}

	
	private Room generateRoom(int choice) {
		RoomImpl roomToRet;
		if(choice == roomNumberWithChild) {
			roomToRet = new RoomImpl(this, Constants.CHILDROOMQUANTITY, this.getRoomNumber() + 1);
		} else {
			roomToRet = new RoomImpl(this, 0, this.getRoomNumber() + 1);
		}
		return roomToRet;
	}
	
	
	@Override
	public Collection<RoomEntity> getAllEntities() {
		return list;
	}


	@Override
	public Collection<Interactable> getInteractable() {
		return interactables;
	}

	@Override
	public int getRoomNumber() {
		return roomNumber;
	}

	@Override
	public Collection<Enemy> getEnemies() {
		return this.enemies;
	}

	@Override
	public Collection<Bullet> getBullets() {
		return bullets;
	}

	@Override
	public Hero getHero() {
		return null;//TODO
	}

	

}
