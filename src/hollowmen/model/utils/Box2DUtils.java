package hollowmen.model.utils;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Filter;
import org.jbox2d.dynamics.FixtureDef;

public class Box2DUtils {

	
	public static String suffix(String s) {
		String[] first = s.split("_");
		return first[0];
	}
	
	public static BodyDef bodyDefCloner(BodyDef subj) {
		BodyDef clone = new BodyDef();
		clone.type = subj.type;
		clone.position = subj.position;
		clone.fixedRotation = subj.fixedRotation;
		return clone;
	}
	
	public static BodyDefBuilder bodyDefBuilder() {
		return new BodyDefBuilder();
	}
	
	public static FilterBuilder filterBuilder() {
		return new FilterBuilder();
	}
	
	public static FixtureDefBuilder fixDefBuilder() {
		return new FixtureDefBuilder();
	}
	
	
	//[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[
	public static class BodyDefBuilder {
		private BodyDef def = new BodyDef();
		
		public BodyDefBuilder() {
			def.fixedRotation = true;
		}
		
		public BodyDefBuilder type(BodyType bodyType) {
			def.type = bodyType;
			return this;
		}
		
		public BodyDefBuilder gravityOff() {
			this.def.setGravityScale(0);
			return this;
		}
		
		public BodyDefBuilder position(float x, float y) {
			def.position = new Vec2(x, y);
			return this;
		}
		
		public BodyDefBuilder rotationOn() {
			def.fixedRotation = false;
			return this;
		}
		
		public BodyDefBuilder position(Vec2 position) {
			def.setPosition(position);
			return this;
		}
		
		public BodyDef build(){
			return this.def;
		}
	}

	public static FixtureDef fixtureDefCloner(FixtureDef subj) {
		return subj;
	}
	
	public static class FixtureDefBuilder {
		public static final String CATEGORY = "cat";
		public static final String MASK = "mask";
		
		private FixtureDef def = new FixtureDef();
		
		public FixtureDefBuilder() {
			def.filter.categoryBits = 0x0000;
			def.filter.maskBits = 0x0000;
			CircleShape shape = new CircleShape();
			shape.setRadius(1f);
			def.shape = shape;
		}
		
		public FixtureDefBuilder density(float density) {
			def.setDensity(density);
			return this;
		}
		
		public FixtureDefBuilder filter(Filter filter) {
			def.filter = filter;
			return this;
		}
		
		public FixtureDefBuilder restitution(float fromZeroToOne) {
			def.restitution = fromZeroToOne;
			return this;
		}
		
		public FixtureDefBuilder friction(float fromZeroToOne) {
			def.setFriction(fromZeroToOne);
			return this;
		}
		
		public FixtureDefBuilder sensor(boolean flag) {
			def.isSensor = flag;
			return this;
		}
		
		public FixtureDefBuilder shape(Shape shape) {
			def.setShape(shape);
			return this;
		}
		
		public FixtureDef build() {
			return def;
		}
	}
	
	public static Filter filterCloner(Filter subj) {
		Filter clone = new Filter();
		clone.categoryBits = subj.categoryBits;
		clone.maskBits = subj.maskBits;
		clone.groupIndex = subj.groupIndex;
		return clone;
	}
	
	public static class FilterBuilder {
		private Filter fil = new Filter();
		
		public FilterBuilder() {
			fil.categoryBits = 0x0000;
			fil.maskBits = 0x0000;
		}
		
		public FilterBuilder addCategory(int i) {
			fil.categoryBits += i;
			return this;
		}
		
		public FilterBuilder addMask(int i) {
			fil.maskBits += i;
			return this;
		}
		
		public FilterBuilder index(int i) {
			fil.groupIndex = i;
			return this;
		}
		
		public Filter build() {
			return fil;
		}
	}
	
}
