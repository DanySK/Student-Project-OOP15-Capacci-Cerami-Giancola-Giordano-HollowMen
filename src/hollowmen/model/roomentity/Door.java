package hollowmen.model.roomentity;

import java.awt.Rectangle;

import hollowmen.model.Information;
import hollowmen.model.Interactable;
import hollowmen.model.dungeon.FloorImpl;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.utils.Constants;
import hollowmen.utilities.ExceptionThrower;

public class Door extends RoomEntityImpl implements Interactable{

	

	private boolean canInteract;
	private int roomNumber;
	
	protected Door(Information info, Rectangle size) {
		super(info, size);
	}
	
	public Door(int doorNumber) {
		this(new InfoImpl("door"), new Rectangle(Constants.DOORSIZE));
		this.roomNumber = doorNumber;
	}
	
	
	@Override
	public void interact() throws IllegalStateException {
		ExceptionThrower.checkIllegalState(this, d -> !d.isInteractAllowed());
		FloorImpl.getInstance().changeRoom(roomNumber);
	}

	@Override
	public void changeInteract() {
		this.canInteract = !this.canInteract;
	}

	@Override
	public Rectangle getSize() {
		return super.getSize();
	}

	@Override
	public Information getInfo() {
		return super.getInfo();
	}

	@Override
	public boolean isInteractAllowed() {
		return this.canInteract;
	}

}
