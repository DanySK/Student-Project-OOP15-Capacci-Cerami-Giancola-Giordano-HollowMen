package hollowmen.model.roomentity;

import hollowmen.model.Information;
import hollowmen.model.Size;
import hollowmen.model.dungeon.FloorImpl;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.utils.Constants;
import hollowmen.utilities.ExceptionThrower;

public class Door extends SettableInteractable{

	
	protected Door(Information info, Size size) {
		super(info, size);
	}

	public Door(int roomNumber) {
		this(new InfoImpl("door"), Constants.DOORSIZE);
		this.roomNumber = roomNumber;
	}
	
	private int roomNumber;
	
	@Override
	public void interact() throws IllegalStateException {
		ExceptionThrower.checkIllegalState(this, d -> !d.isInteractAllowed());
		FloorImpl.getInstance().changeRoom(roomNumber);
	}

}
