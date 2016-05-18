package hollowmen.model.dungeon;

import hollowmen.utilities.ExceptionThrower;

public class Door extends SettableInteractable{

	private int roomNumber;
	
	public Door(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	@Override
	public void interact() throws IllegalStateException {
		ExceptionThrower.checkIllegalState(this, d -> !d.isInteractAllowed());
		FloorImpl.getInstance().changeRoom(roomNumber);
	}
	
}
