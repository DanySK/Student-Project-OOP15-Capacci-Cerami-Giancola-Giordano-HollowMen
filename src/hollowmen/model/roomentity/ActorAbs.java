package hollowmen.model.roomentity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jbox2d.common.Vec2;

import hollowmen.enumerators.ActorState;
import hollowmen.enumerators.ParamName;
import hollowmen.model.Actor;
import hollowmen.model.Information;
import hollowmen.model.Parameter;
import hollowmen.model.roomentity.typeaction.Attack;
import hollowmen.model.utils.Constants;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

public abstract class ActorAbs extends RoomEntityAbs implements Actor{

	private String state;
	private boolean facingRight;
	protected ActionAllowed actionAllowed = new ActionAllowedImpl();
	private Map<String, Parameter> parameters;
	private Collection<Information> status;
	
	
	public ActorAbs(Information info, Pair<Float, Float> size, Collection<Parameter> param) {
		super(info, size);
		this.parameters = new HashMap<>();
		param.stream().forEach(p -> this.parameters.put(p.getInfo().getName(), p));
		parameters.put(Parameter.ParamName.HP.toString(),
				new HPclass(this.parameters.get(Parameter.ParamName.HPMAX.toString()), this));
		actionAllowed.getActionAllowed().put(Actor.Action.JUMP.toString(), () -> this.jump());
		actionAllowed.getActionAllowed().put(Actor.Action.ATTACK.toString(), () -> this.attack());
	}

	@Override
	public void performAction(String action) throws NullPointerException {
		ExceptionThrower.checkNullPointer(action);
		ExceptionThrower.checkIllegalArgument(action, a -> this.actionAllowed.getActionAllowed().containsKey(a));
		actionAllowed.getActionAllowed().get(action).run();
	}

	public void move(String d) {
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

	public ActionAllowed getAction() {
		return this.actionAllowed;
	}
	
	@Override
	public Collection<Information> getStatus() {
		return this.status;
	}
		
	private void jump() {
		if(!this.getState().equals(ActorState.ATTACKING.toString())) {
			this.getBody().applyLinearImpulse(Constants.JUMPFORCE, this.getBody().getLocalCenter(), true);
		}
	}
	
	private void attack() {
		new Attack().doAction(this);
	}

}
