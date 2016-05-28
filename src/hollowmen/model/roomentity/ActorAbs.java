package hollowmen.model.roomentity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jbox2d.common.Vec2;

import hollowmen.enumerators.ParamName;
import hollowmen.model.Actor;
import hollowmen.model.Information;
import hollowmen.model.Parameter;
import hollowmen.model.Status;
import hollowmen.model.TypeAction;
import hollowmen.model.utils.Constants;
import hollowmen.utilities.ExceptionThrower;

public abstract class ActorAbs extends RoomEntityAbs implements Actor{

	private String state;
	private boolean facingRight;
	private ActionAllowed actionAllowed;
	private Map<String, Parameter> parameters;
	private Collection<Status> status;
	
	
	public ActorAbs(Information info, ActionAllowed aA, Collection<Parameter> param) {
		super(info);
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

	public final void move(String d) {
		changeFacing(d);
		float speed = (float) this.getParameters().get(ParamName.MOVSPEED.toString()).getValue();
		if(this.getBody().getLinearVelocity().abs().x < speed * Constants.MAXSPEED){
			this.getBody().applyForceToCenter(new Vec2(this.isFacingRight() ? speed : -speed, 0));
		} else {
			this.getBody().applyForceToCenter(new Vec2(this.isFacingRight() ? Constants.FLATSPEED : -Constants.FLATSPEED, 0));
		}
		
	}
	
	private void changeFacing(String d) {
		if(d.equals(Direction.RIGHT.toString()) && !this.isFacingRight() 
				|| d.equals(Direction.LEFT.toString()) && this.isFacingRight()) {
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
