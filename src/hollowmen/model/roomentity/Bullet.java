package hollowmen.model.roomentity;

import java.util.Collection;

import hollowmen.model.Actor;
import hollowmen.model.Attack;
import hollowmen.model.Information;
import hollowmen.model.Parameter;
import hollowmen.model.RoomEntity;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.utils.Constants;
import hollowmen.utilities.Pair;

public class Bullet extends AttackAbs implements Attack{
	
	
	public Bullet( Actor owner,Collection<Parameter> param, String direction) {
		super(new InfoImpl(RoomEntity.RoomEntityName.BULLET.toString()), Constants.BULLET_SIZE, param, owner, direction);
	}
	
}
