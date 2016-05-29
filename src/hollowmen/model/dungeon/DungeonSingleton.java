package hollowmen.model.dungeon;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import hollowmen.model.Dungeon;
import hollowmen.model.Hero;
import hollowmen.model.LimitedCounter;
import hollowmen.model.Room;
import hollowmen.model.Shop;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.model.utils.Constants;
import hollowmen.model.utils.GameOverException;
import hollowmen.model.utils.SimpleLimitedCounter;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

public class DungeonSingleton implements Dungeon{

	private final float GRAVITY = -9.8f;
	private final float THICKNESS = 2.5f;
	private final int ITERATION_VELOCITY = 6;
	private final int ITERATION_POSITION = 3;
	
	private Room lobby;
	
	private LimitedCounter unlockedFloors;
	
	private int floorNumber = 0;
	
	private Room currentRoom;
	
	private Hero hero;
	
	private Difficulty diff;
	
	private World world;
	
	private boolean gameOver = false;
	
	private DungeonSingleton() {
		this.world = new World(new Vec2(0, GRAVITY));
		setUpBorder();
	};
	
	public static DungeonSingleton getInstance() {
		return Holder.Instance;
	}
	
	private static class Holder {
		public static DungeonSingleton Instance =  new DungeonSingleton();
	}
	
	@Override
	public void update(long deltaTime) throws GameOverException {
		if(gameOver) {
			endRun();
			throw new GameOverException();
		}
		world.step(deltaTime, ITERATION_VELOCITY, ITERATION_POSITION);
		
	}
	
	@Override
	public int getFloorNumber() {
		return this.floorNumber;
	}

	@Override
	public Room getCurrentRoom() {
		return (this.floorNumber == 0)?this.lobby:this.currentRoom;
	}

	public void changeRoom(int newRoomNumber) {
		if(newRoomNumber < 0) {
			this.currentRoom = this.currentRoom.getParentRoom();
		} else {
			this.currentRoom = this.currentRoom.getChildRoom(newRoomNumber);
			if(this.currentRoom.getRoomNumber() > Constants.ROOM_TO_VISIT) {
				endRun();
			}
		}
	}
	
	@Override
	public void goTo(int floorNumber) throws IllegalStateException, NullPointerException {
		ExceptionThrower.checkIllegalState(floorNumber, f -> f > this.unlockedFloors.getValue());
		this.floorNumber = floorNumber;
		this.currentRoom = new RoomImpl(this.lobby, Constants.CHILDROOMQUANTITY, 1);
	}

	@Override
	public Pair<Integer, Integer> getFloors() {
		return new Pair<>((int) this.unlockedFloors.getValue(), (int) this.unlockedFloors.getLimit());
	}

	@Override
	public void endRun() {
		if(this.currentRoom.getRoomNumber() > Constants.ROOM_TO_VISIT
				&& this.floorNumber == this.unlockedFloors.getValue()) {
			this.unlockedFloors.addToValue(1);
		}
		this.gameOver = false;
		this.floorNumber = 0;
	}

	@Override
	public int getDifficulty() {
		return this.diff.ordinal()-1;
	}

	@Override
	public Shop getShop() {
		return null;
	}

	@Override
	public Hero getHero() {
		return this.hero;
	}

	@Override
	public World getWorld() {
		return this.world;
	}
	
	private void setUpBorder() {

		float halfLenght = (float)(Constants.WORLD_SIZE.getWidth()/2);
		float halfHeight = (float)(Constants.WORLD_SIZE.getHeight()/2);


		PolygonShape polygonShape = new PolygonShape();

		BodyDef bodyDef = new Box2DUtils.BodyDefBuilder()
				.type(BodyType.STATIC)
				.position((float) Constants.WORLD_SIZE.getCenterX(), (float) Constants.WORLD_SIZE.getCenterY())
				.build();

		FixtureDef groundFix = new Box2DUtils.FixtureDefBuilder()
				.friction(0)
				.restitution(0)
				.shape(polygonShape)
				.filter(new Box2DUtils.FilterBuilder()
						.addCategory(FilterType.GROUND.getValue())
						.addMask(0xffff)
						.build())
				.build();

		FixtureDef wallFix = Box2DUtils.fixtureDefCloner(groundFix);
		wallFix.filter.categoryBits = FilterType.WALL.getValue();

		Body body = world.createBody(bodyDef);

		//ground
		polygonShape.setAsBox(halfLenght+THICKNESS, THICKNESS, new Vec2(0, -(halfHeight+THICKNESS)), 0);
		body.createFixture(groundFix);
		//top
		polygonShape.setAsBox(halfLenght+THICKNESS, THICKNESS, new Vec2(0, halfHeight+THICKNESS), 0);
		body.createFixture(groundFix);
		//left
		polygonShape.setAsBox( THICKNESS, halfHeight+THICKNESS, new Vec2(-(halfLenght+THICKNESS), 0), 0);
		body.createFixture(groundFix);
		//right
		polygonShape.setAsBox( THICKNESS, halfHeight+THICKNESS, new Vec2(halfLenght+THICKNESS, 0), 0);
		body.createFixture(groundFix);
	}

	public void setDifficulty(Difficulty diff) {
		this.diff = diff;
	}
	
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	
	public void setUnlockedFloor(int lastUnlock, int maxFloor) {
		this.unlockedFloors = new SimpleLimitedCounter(lastUnlock, maxFloor);
	}
	
	

}
