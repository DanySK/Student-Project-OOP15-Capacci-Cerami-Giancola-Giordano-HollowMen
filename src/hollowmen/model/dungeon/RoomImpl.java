package hollowmen.model.dungeon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import hollowmen.model.Interactable;
import hollowmen.model.Room;
import hollowmen.model.RoomEntity;
import hollowmen.model.roomentity.interactable.Door;
import hollowmen.model.utils.Algorithms;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.model.utils.Constants;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.RandomSelector;
import hollowmen.enumerators.RoomEntityName;
import hollowmen.model.Attack;
import hollowmen.model.Enemy;

public class RoomImpl implements Room{
	
	private boolean needToGenerate = true;
	
	private Room parentRoom;
	
	private int childNumber;
		
	private List<Room> childRoom = new ArrayList<>();
	
	private Collection<Interactable> interactables = new LinkedList<>();
	
	private Collection<Enemy> enemies = new LinkedList<>();;
	
	private Collection<Attack> bullets = new LinkedList<>();;
	
	private List<RoomEntity> list = new LinkedList<>();;
	
	private int roomNumber;
	
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
	};
	
	public void autoPopulate() {
		if(this.needToGenerate) {
			this.needToGenerate = false;
			for(int i = 0; i < this.childNumber; i++) {
				this.interactables.add(new Door(RoomEntityName.DOOR.toString(), i));
			}
			Box2DUtils.linearSpacing(this.interactables);
			Interactable backDoor = new Door(RoomEntityName.DOOR_BACK.toString(), -1);
			Box2DUtils.centerPosition(backDoor);
			this.interactables.add(backDoor);
			if(this.childNumber == 0) {
				Algorithms.populateRoom(this);
			}
		}
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
	public Collection<Attack> getBullets() {
		return Collections.unmodifiableCollection(bullets);
	}

	@Override
	public int getRoomNumber() {
		return roomNumber;
	}

	@Override
	public void addEntity(RoomEntity roomEntity) {
		list.add(roomEntity);
		if(roomEntity instanceof Attack) {
			bullets.add((Attack) roomEntity);
		}
		if(roomEntity instanceof Enemy) {
			enemies.add((Enemy) roomEntity);
		}
		if(roomEntity instanceof Interactable) {
			interactables.add((Interactable)roomEntity);
		}
	}

	@Override
	public void removeEntity(RoomEntity roomEntity) throws IllegalArgumentException {
		ExceptionThrower.checkIllegalArgument(roomEntity, x -> !this.list.contains(x));
		list.remove(roomEntity);
		if(roomEntity instanceof Attack) {
			bullets.remove((Attack) roomEntity);
		}
		if(roomEntity instanceof Enemy) {
			enemies.remove((Enemy) roomEntity);
		}
		if(roomEntity instanceof Interactable) {
			interactables.remove((Interactable)roomEntity);
		}
	}



}
