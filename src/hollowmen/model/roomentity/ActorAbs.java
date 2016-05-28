package hollowmen.model.roomentity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jbox2d.common.Vec2;

import hollowmen.enumerators.ActorState;
import hollowmen.enumerators.ParamName;
import hollowmen.enumerators.StatusName;
import hollowmen.model.Actor;
import hollowmen.model.Information;
import hollowmen.model.Parameter;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.dungeon.time.TimerSingleton;
import hollowmen.model.utils.Constants;
import hollowmen.utilities.ExceptionThrower;

public abstract class ActorAbs extends RoomEntityAbs implements Actor{

	private String state;
	private boolean facingRight;
	private ActionAllowed actionAllowed;
	private Map<String, Parameter> parameters;
	private Collection<Information> status;
	
	
	public ActorAbs(Information info, Collection<Parameter> param) {
		super(info);
		this.parameters = new HashMap<>();
		param.stream().forEach(p -> parameters.put(p.getInfo().getName(), p));
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
		if(!this.getStatus().contains(StatusName.RECHARGE.toString())
				&& !this.getState().equals(ActorState.ATTACKING.toString())) {
			this.setState(ActorState.ATTACKING.toString());
			if(this.getParameters().get(ParamName.ATTACKRANGE.toString()).getValue() <= 0) {
				createProjectile(this);
			}
			TimerSingleton.getInstance().register(this, Constants.ATTACK_DURATION,
					x -> {
						x.setState(ActorState.STANDING.toString());
						x.getStatus().add(new InfoImpl(StatusName.RECHARGE.toString()));
						TimerSingleton.getInstance().register(x, x.getParameters().get(ParamName.ATTACKSPEED.toString()).getValue(),
								y -> y.getStatus().remove(new InfoImpl(StatusName.RECHARGE.toString())));
					});
		}
	}

	private void createProjectile(ActorAbs actorAbs) {
		
	}
	
}
