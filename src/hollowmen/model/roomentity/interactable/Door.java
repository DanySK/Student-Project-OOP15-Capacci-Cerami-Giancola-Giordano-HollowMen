package hollowmen.model.roomentity.interactable;

import java.awt.Rectangle;

import hollowmen.model.dungeon.FloorSingleton;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.roomentity.UselessInteractable;
import hollowmen.model.utils.Constants;

public class Door extends UselessInteractable{

	private int roomNumber;
	
	
	public Door(int doorNumber, int ID) {
		super(new InfoImpl("door"), new Rectangle(Constants.DOOR_SIZE), ID);
		this.roomNumber = doorNumber;
	}
	
	@Override
	public void interact() throws IllegalStateException {
		super.interact();
		FloorSingleton.getInstance().changeRoom(roomNumber);		
	}

	public int getRoomNumber() {
		return roomNumber;
	}

}
