package hollowmen.model.dungeon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import hollowmen.model.Interactable;
import hollowmen.model.Room;
import hollowmen.model.RoomEntity;
import hollowmen.model.roomentity.interactable.Door;
import hollowmen.model.utils.Constants;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.RandomSelector;
import hollowmen.model.Bullet;
import hollowmen.model.Enemy;
import hollowmen.model.Hero;

public class RoomImpl implements Room{
	
	private Room parentRoom;
	
	private int childNumber;
	
	private int IDCounter;
	
	private List<Room> childRoom = new ArrayList<>();
	
	private Collection<Interactable> interactables;
	
	private Collection<Enemy> enemies;
	
	private Collection<Bullet> bullets;
	
	private List<RoomEntity> list;
	
	private int roomNumber;
	
	//TODO migliorare l'intervallo dei numeri mettendo numeri interi che vanno da 0 a Constants...
	private final int roomNumberWithChild = RandomSelector.getIntFromRange(0, Constants.CHILDROOMQUANTITY); 
	
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
			this.interactables.add(new Door(i, this.getIDCounter()));
		}
		this.interactables.add(new Door(-1, this.getIDCounter()));
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
		return Collections.unmodifiableList(list);
	}


	@Override
	public Collection<Interactable> getInteractable() {
		return Collections.unmodifiableCollection(interactables);
	}
	
	@Override
	public Collection<Enemy> getEnemies() {
		return Collections.unmodifiableCollection(enemies);
	}

	@Override
	public Collection<Bullet> getBullets() {
		return Collections.unmodifiableCollection(bullets);
	}

	@Override
	public Hero getHero() {
		return null;//TODO
	}

	@Override
	public int getRoomNumber() {
		return roomNumber;
	}


	@Override
	public void addEntity(RoomEntity roomEntity) {
		list.add(roomEntity);
		if(roomEntity instanceof Bullet) {
			bullets.add((Bullet) roomEntity);
		}
		if(roomEntity instanceof Enemy) {
			enemies.add((Enemy) roomEntity);
		}
		if(roomEntity instanceof Interactable) {
			interactables.add((Interactable)roomEntity);
		}
		//CollisionManager add((casting) roomEntity)
	}

	@Override
	public void removeEntity(RoomEntity roomEntity) throws IllegalArgumentException {
		ExceptionThrower.checkIllegalArgument(roomEntity, x -> this.list.contains(x));
		list.remove(roomEntity);
		if(roomEntity instanceof Bullet) {
			bullets.remove((Bullet) roomEntity);
		}
		if(roomEntity instanceof Enemy) {
			enemies.remove((Enemy) roomEntity);
		}
		if(roomEntity instanceof Interactable) {
			interactables.remove((Interactable)roomEntity);
		}
		//CollisionManager remove((casting) roomEntity)
	}

	public int getIDCounter() {
		this.IDCounter++;
		return this.IDCounter;
	}

}
