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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roomNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Door other = (Door) obj;
		if (roomNumber != other.roomNumber)
			return false;
		return true;
	}
	
}
