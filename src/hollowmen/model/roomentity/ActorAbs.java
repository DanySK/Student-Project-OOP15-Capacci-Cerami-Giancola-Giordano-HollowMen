package hollowmen.model.roomentity;


import java.awt.Rectangle;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import hollowmen.model.Actor;
import hollowmen.model.Information;
import hollowmen.model.Parameter;
import hollowmen.model.Status;
import hollowmen.model.TypeAction;
import hollowmen.utilities.ExceptionThrower;

public abstract class ActorAbs extends RoomEntityImpl implements Actor{

	private String state;
	private boolean facingRight;
	private ActionAllowed actionAllowed;
	private Map<String, Parameter> parameters;
	private Collection<Status> status;
	
	
	public ActorAbs(Information info, Rectangle size, int ID, ActionAllowed aA, Collection<Parameter> param) {
		super(info, size, ID);
		this.actionAllowed = aA;
		this.parameters = new HashMap<>();
		param.stream().forEach(p -> parameters.put(p.getInfo().getName(), p));
	}
	
	public ActionAllowed getActionAllowed() {
		return this.actionAllowed;
	}

	@Override
	public void performAction(String action) throws NullPointerException {
		ExceptionThrower.checkNullPointer(action);
		TypeAction actionToPerform = actionAllowed.getActionAllowed().get(action);
		if(actionToPerform != null) {
			actionToPerform.doAction(this);
		}
	}

	public final void move(Direction d) {
		changeFacing(d);
		if(!d.equals(Direction.STOP)) {
			//CollisionManager.move(this);
		}
	}
	
	private void changeFacing(Direction d) {
		if(d.equals(Direction.RIGHT) && !this.isFacingRight() 
				|| d.equals(Direction.LEFT) && this.isFacingRight()) {
			this.setFacingRight(!this.isFacingRight());
		}
	}
	
	@Override
	public Map<String, Parameter> getParameters() {
		return this.parameters;
	}

	
	
	public void setFacingRight(boolean b) {
		this.facingRight = b;
	}
	
	@Override
	public boolean isFacingRight() {
		return facingRight;
	}

	@Override
	public String getState() {
		return this.state;
	}

	@Override
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public Collection<Status> getStatus() {
		return this.status;
	}
	
}
