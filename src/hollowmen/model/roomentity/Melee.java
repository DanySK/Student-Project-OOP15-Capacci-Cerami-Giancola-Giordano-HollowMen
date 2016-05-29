package hollowmen.model.roomentity;

import java.util.Collection;

import hollowmen.enumerators.ParamName;
import hollowmen.model.Actor;
import hollowmen.model.Parameter;
import hollowmen.model.dungeon.time.TimerSingleton;
import hollowmen.model.utils.Constants;

public class Melee extends BulletImpl{

	public Melee(Actor owner, Collection<Parameter> param, String direction) {
		super(owner, param, direction);
		TimerSingleton.getInstance().register(this, Constants.ATTACK_DURATION, x -> x.dispose());
	}
	
	@Override
	public void move(String s) {};
	
	@Override
	public float getLength() {
		return (float) super.getOwner().getParameters()
				.get(ParamName.ATTACKRANGE.toString()).getValue();
	}
	
}
