package hollowmen.model.roomentity;

import hollowmen.model.Information;
import hollowmen.model.Interactable;
import hollowmen.model.Size;

public abstract class SettableInteractable extends RoomEntityImpl implements Interactable{

	
	protected SettableInteractable(Information info, Size size) {
		super(info, size);
		// TODO Auto-generated constructor stub
	}

	private boolean can;
	
	@Override
	public boolean isInteractAllowed() {
		return can;
	}

	public void setInteract(boolean b) {
		can = b;
	}
	
	@Override
	public abstract void interact() throws IllegalStateException;
}
