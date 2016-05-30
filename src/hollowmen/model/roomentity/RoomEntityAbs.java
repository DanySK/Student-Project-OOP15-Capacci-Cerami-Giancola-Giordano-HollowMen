package hollowmen.model.roomentity;

import java.util.Collection;
import java.util.LinkedList;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.Filter;
import org.jbox2d.dynamics.FixtureDef;

import hollowmen.model.Information;
import hollowmen.model.RoomEntity;
import hollowmen.model.dungeon.DungeonSingleton;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.utilities.Pair;

public abstract class RoomEntityAbs implements RoomEntity{

	private Information info;
	private Body body;
	private float length;
	private float height;
	
	protected RoomEntityAbs(Information info2, Pair<Float, Float> size) {
		this.info = new InfoImpl(info2);
		this.length = size.getX();
		this.height = size.getY();
		this.body = DungeonSingleton.getInstance().getWorld().createBody(this.defBody());
		this.defFixture().stream().forEach(x -> this.body.createFixture(x));
		DungeonSingleton.getInstance().getCurrentRoom().addEntity(this);
		this.getBody().setUserData(this);
	}
	
	public abstract BodyDef defBody();
	
	public abstract Collection<FixtureDef> defFixture();
	
	@Override
	public Information getInfo() {
		return info;
	}

	@Override
	public Body getBody() {
		return body;
	}

	public void dispose() {
		DungeonSingleton.getInstance().getDisposeList().add(this);
	}
	
	
	@Override
	public float getLength() {
		return this.length;
	}

	@Override
	public float getHeight() {
		return this.height;
	}
	
	public Collection<FixtureDef> generateRectangleFix(Filter filter, boolean sensor) {
		PolygonShape shape = new PolygonShape();
		float halfLength = (float) (this.getLength() / 2);
		float halfHeight = (float) (this.getHeight() / 2);
		shape.setAsBox(halfLength, halfHeight, this.getBody().getLocalCenter(), 0);
		FixtureDef def =new Box2DUtils.FixtureDefBuilder()
				.shape(shape)
				.friction(0)
				.filter(filter)
				.sensor(sensor)
				.build();
		Collection<FixtureDef> retValue = new LinkedList<>();
		retValue.add(def);
		return retValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomEntityAbs other = (RoomEntityAbs) obj;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		return true;
	}


}
