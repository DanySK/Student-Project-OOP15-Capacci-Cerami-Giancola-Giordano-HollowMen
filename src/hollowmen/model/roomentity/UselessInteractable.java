package hollowmen.model.roomentity;

import java.awt.Rectangle;

import hollowmen.model.Information;
import hollowmen.model.Interactable;
import hollowmen.utilities.ExceptionThrower;

public class UselessInteractable extends RoomEntityImpl implements Interactable{

	private boolean canInteract = false;
	
	
	public UselessInteractable(Information info, Rectangle size) {
		super(info, size);
	}
	
	@Override
	public boolean isInteractAllowed() {
		return this.canInteract;
	}

	@Override
	public void changeInteract() {
		this.canInteract = !this.canInteract;
	}

	@Override
	public void interact() throws IllegalStateException {
		ExceptionThrower.checkIllegalState(this, d -> !d.isInteractAllowed());
	}; 
}
