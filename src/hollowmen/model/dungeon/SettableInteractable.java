package hollowmen.model.dungeon;

import hollowmen.model.Interactable;

public abstract class SettableInteractable extends RoomEntityImpl implements Interactable{

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
