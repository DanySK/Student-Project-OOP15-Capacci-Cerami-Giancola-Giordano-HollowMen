package hollowmen.model.roomentity;

import org.jbox2d.dynamics.Filter;

import hollowmen.model.Information;
import hollowmen.model.Interactable;
import hollowmen.model.collision.hitbox.FilterType;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.utilities.ExceptionThrower;

public abstract class UselessInteractable extends RoomEntityAbs implements Interactable{

	private boolean canInteract = false;
	
	
	public UselessInteractable(Information name) {
		super(name);
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

	public Filter standardFilter(){
		return Box2DUtils.filterBuilder()
				.addCategory(FilterType.LOOTABLE.getValue())
				.addMask(FilterType.GROUND.getValue())
				.addMask(FilterType.HERO.getValue())
				.build();
	}
}
