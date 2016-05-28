package hollowmen.model.roomentity;

import hollowmen.model.Information;
import hollowmen.model.Interactable;
import hollowmen.utilities.ExceptionThrower;

public abstract class UselessInteractable extends RoomEntityImpl implements Interactable{

	private boolean canInteract = false;
	
	
	public UselessInteractable(Information info) {
		super(info);
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
	}

	
}
