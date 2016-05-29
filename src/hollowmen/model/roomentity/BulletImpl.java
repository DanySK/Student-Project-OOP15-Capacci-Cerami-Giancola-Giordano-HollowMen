package hollowmen.model.roomentity;

import java.util.Collection;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Filter;
import org.jbox2d.dynamics.FixtureDef;

import hollowmen.enumerators.RoomEntityName;
import hollowmen.model.Actor;
import hollowmen.model.Attack;
import hollowmen.model.Parameter;
import hollowmen.model.collision.hitbox.FilterType;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.utils.Constants;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.utilities.ExceptionThrower;

public class BulletImpl extends ActorAbs implements Attack{

	private final Actor owner;
	private final String direction;
	
	
	public BulletImpl(Actor owner, Collection<Parameter> param, String direction) {
		super(new InfoImpl(RoomEntityName.BULLET.toString()), Constants.BULLET_SIZE, param);
		this.direction = direction;
		this.owner = owner;
	}

	@Override
	public void performAction(String action) {
		ExceptionThrower.checkIllegalArgument(this, x -> true);
	}
	
	@Override
	public void move(String s) {
		super.move(direction);
	}
	
	@Override
	public BodyDef defBody() {
		Body temp = this.owner.getBody();
		float xPos = this.direction.equals(Actor.Direction.RIGHT.toString())
				? temp.getLocalCenter().x + this.owner.getLength() / 2 
				: temp.getLocalCenter().x - this.owner.getLength() / 2;
			
		return Box2DUtils.bodyDefBuilder()
				.type(BodyType.DYNAMIC)
				.gravityOff()
				.position(xPos, temp.getLocalCenter().y)
				.build();
	}

	@Override
	public Collection<FixtureDef> defFixture() {
		Filter filter;
		if(this.owner.getInfo().equals(RoomEntityName.HERO.toString())) {
			filter = Box2DUtils.filterBuilder()
					.addCategory(FilterType.HEROATTACK.getValue())
					.addMask(FilterType.GROUND.getValue())
					.addMask(FilterType.ENEMY.getValue())
					.build();
		} else {
			filter = Box2DUtils.filterBuilder()
					.addCategory(FilterType.ENEMYATTACK.getValue())
					.addMask(FilterType.GROUND.getValue())
					.addMask(FilterType.HERO.getValue())
					.build();
		}	
		return this.generateRectangleFix(filter, false);
	}

	@Override
	public Actor getOwner() {
		return this.owner;
	}

}
