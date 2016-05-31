package hollowmen.model.dungeon;

import java.util.Collection;
import java.util.LinkedList;

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
import hollowmen.model.Pokedex;
import hollowmen.model.Room;
import hollowmen.model.RoomEntity;
import hollowmen.model.Shop;
import hollowmen.model.Time;
import hollowmen.model.dungeon.time.TimerSingleton;
import hollowmen.model.utils.Actors;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.model.utils.Constants;
import hollowmen.model.utils.GameOverException;
import hollowmen.model.utils.SimpleLimitedCounter;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

public class DungeonSingleton implements Dungeon{

	private final float GRAVITY = -9.8f;
	private final float THICKNESS = 20f;
	private final int ITERATION_VELOCITY = 6;
	private final int ITERATION_POSITION = 3;
	
	private Room lobby = new Lobby();
	
	private LimitedCounter unlockedFloors;
	
	private int floorNumber = 0;
	
	private Room currentRoom;
	
	private Shop shop;
	
	private Hero hero;
	
	private Difficulty diff;
	
	private World world;
	
	private Pokedex poke;
	
	private Collection<RoomEntity> disposeList = new LinkedList<>();
	
	private boolean gameOver = false;
	
	private DungeonSingleton() {
		this.world = new World(new Vec2(0, GRAVITY));
		setUpBorder();
		this.world.setContactListener(new GameCollisionListener());
	};
	
	public static DungeonSingleton getInstance() {
		return Holder.Instance;
	}
	
	private static class Holder {
		public static DungeonSingleton Instance =  new DungeonSingleton();
	}
	
	@Override
	public void update(long deltaTime) throws GameOverException {
		this.disposeList.stream().forEach(r -> {
			world.destroyBody(r.getBody());
			this.currentRoom.removeEntity(r);
		});
		if(gameOver) {
			endRun();
			throw new GameOverException();
		}
		TimerSingleton.getInstance().addToValue(deltaTime);
		this.getCurrentRoom().getEnemies().stream().forEach(x -> x.move("By Pattern"));
		this.getCurrentRoom().getBullets().stream().forEach(x -> x.move("By Yourself"));
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
		Box2DUtils.centerPosition(this.hero);
		if(newRoomNumber < 0) {
			this.currentRoom = this.currentRoom.getParentRoom();
		} else {
			this.currentRoom = this.currentRoom.getChildRoom(newRoomNumber);
			if(this.currentRoom.getRoomNumber() > Constants.ROOM_TO_VISIT) {
				endRun();
			}
		}
		this.poke.checkNewEnemy(this.currentRoom);
	}
	
	@Override
	public void goTo(int floorNumber) throws IllegalStateException, NullPointerException {
		ExceptionThrower.checkIllegalState(floorNumber, f -> f > this.unlockedFloors.getValue());
		TimerSingleton.getInstance().resetAndLimit(1000000);
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
		Actors.regenerate(this.hero);
	}

	@Override
	public Difficulty getDifficulty() {
		return this.diff;
	}

	@Override
	public Shop getShop() {
		return this.shop;
	}

	@Override
	public Hero getHero() {
		return this.hero;
	}

	@Override
	public World getWorld() {
		return this.world;
	}
	
	
	
	public void gameOver() {
		this.gameOver = true;
	}
	
	private void setUpBorder() {

		float halfLength = (float)(Constants.WORLD_SIZE.getWidth()/2);
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
		wallFix.getFilter().categoryBits = FilterType.WALL.getValue();
		
		FixtureDef airLine = Box2DUtils.fixtureDefCloner(groundFix);
		airLine.getFilter().categoryBits = FilterType.WALL.getValue();
		airLine.getFilter().maskBits = FilterType.FLYLINE.getValue();
		
		Body body = world.createBody(bodyDef);

		//ground
		polygonShape.setAsBox(halfLength+THICKNESS, THICKNESS, new Vec2(0, -(halfHeight+THICKNESS)), 0);
		body.createFixture(groundFix);
		//top
		polygonShape.setAsBox(halfLength+THICKNESS, THICKNESS, new Vec2(0, halfHeight+THICKNESS), 0);
		body.createFixture(groundFix);
		//left
		polygonShape.setAsBox( THICKNESS, halfHeight+THICKNESS, new Vec2(-(halfLength+THICKNESS), 0), 0);
		body.createFixture(groundFix);
		//right
		polygonShape.setAsBox( THICKNESS, halfHeight+THICKNESS, new Vec2(halfLength+THICKNESS, 0), 0);
		body.createFixture(groundFix);
		//flyLine
		polygonShape.setAsBox(halfLength+THICKNESS, THICKNESS, new Vec2(0, 0), 0);
		body.createFixture(groundFix);
	}
	
	@Override
	public Pokedex getPokedex() {
		return this.poke;
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
	
	public void setPokedex(Pokedex poke) {
		this.poke = poke;
	}

	public void addToDisposeList(RoomEntity re) {
		this.disposeList.add(re);
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	@Override
	public Time getTimer() {
		return TimerSingleton.getInstance();
	}

}
