package hollowmen.model.roomentity.hero;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Filter;
import org.jbox2d.dynamics.FixtureDef;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;

import hollowmen.enumerators.RoomEntityName;
import hollowmen.model.Hero;
import hollowmen.model.HeroClass;
import hollowmen.model.Information;
import hollowmen.model.Inventory;
import hollowmen.model.Item;
import hollowmen.model.Item.ItemState;
import hollowmen.model.LimitedCounter;
import hollowmen.model.Lootable;
import hollowmen.model.Parameter;
import hollowmen.model.Pokedex;
import hollowmen.model.Slot;
import hollowmen.model.TargetPointSystem;
import hollowmen.model.dungeon.FilterType;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.roomentity.ActorAbs;
import hollowmen.model.utils.Actors;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.model.utils.Constants;
import hollowmen.model.utils.SimpleLimitedCounter;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

public class HeroImpl extends ActorAbs implements Hero{

	private final Pair<Float, Float> BODY_PROP = new Pair<>(0.4f, 0.45f);
	private final float HEAD_PROP = 0.8f;
	
	
	private LimitedCounter exp;
	
	private int level;
	
	private int gold;
	
	private Inventory inventory;
	
	private Pokedex pokedex;
	
	private HeroClass heroClass;
	
	private TargetPointSystem<Parameter> uppableParam;
	
	private ListMultimap<String, Slot> slots = ArrayListMultimap.create();
	
	public HeroImpl(int level, int gold, Pair<Integer, Integer> exp, Information info, 
			HeroClass heroClass, Pokedex pokedex, TargetPointSystem<Parameter> statSystem,
			Inventory inventory, Collection<Item> equippedItems) {
		super(new InfoImpl(RoomEntityName.HERO.toString()), Constants.HERO_SIZE, heroClass.getBaseParam());
		
	}
	
	@Override
	public void equipItem(Item item) throws IllegalStateException, NullPointerException {
		ExceptionThrower.checkNullPointer(item);
		ExceptionThrower.checkIllegalState(item, i -> !i.getState().equals(ItemState.UNEQUIPPED));
		ExceptionThrower.checkIllegalArgument(item, i -> !i.getHeroClassEquippable().equals(this.getHeroClass()));
		for(Slot s : slots.get(item.getSlot())) {
			if(s.getEquippedItem().isPresent()) {
				continue;
			} else {
				s.setItem(Optional.of(item));
				item.setState(ItemState.EQUIPPED);
				item.getModifiers().entries().stream()
					.forEach(e -> Actors.addModifier(this, e.getValue()));
				this.inventory.removeItem(item);
				return;
			}
		}
		this.unequipItem(slots.get(item.getSlot()).get(0).getEquippedItem().get());
		this.equipItem(item);
	}

	@Override
	public void unequipItem(Item item) throws IllegalStateException, NullPointerException {
		ExceptionThrower.checkNullPointer(item);
		ExceptionThrower.checkIllegalState(item, i -> !i.getState().equals(ItemState.EQUIPPED));
		Item temp;
		for(Slot s : slots.get(item.getSlot())) {
			temp = s.getEquippedItem().isPresent() ? s.getEquippedItem().get() : null;
			if(temp == null) {
				continue;
			}
			if(temp.equals(item)) {
				temp.setState(ItemState.UNEQUIPPED);
				temp.getModifiers().entries().stream()
					.forEach(e -> Actors.removeModifier(this, e.getValue()));
				this.inventory.addItem(item);
				s.setItem(Optional.empty());
			}
		}
	}

	
	@Override
	public void sellItem(Item item) throws IllegalStateException, IllegalArgumentException, NullPointerException {
		ExceptionThrower.checkNullPointer(item);
		ExceptionThrower.checkIllegalState(item, i -> i.getState().equals(ItemState.BUYABLE));
		this.inventory.removeItem(item);
		if(item.getState().equals(ItemState.EQUIPPED)) {
			this.unequipItem(item);
		}
		this.gold += item.getGoldValue();
	}

	@Override
	public void buyItem(Item item) throws IllegalStateException, NullPointerException {
		ExceptionThrower.checkNullPointer(item);
		ExceptionThrower.checkIllegalState(item, i -> (i.getGoldValue() - this.getGold()) < 0);
		item.setState(ItemState.UNEQUIPPED);
		this.inventory.addItem(item);
		this.gold -= item.getGoldValue();
	}

	@Override
	public Pair<Integer, Integer> getExp() {
		return new Pair<Integer, Integer>((int)this.exp.getValue(), (int)this.exp.getLimit());
	}

	@Override
	public int getLevel() {
		return this.level;
	}

	@Override
	public int getGold() {
		return this.gold;
	}

	@Override
	public Inventory getInventory() {
		return this.inventory;
	}

	@Override
	public Pokedex getPokedex() {
		return this.pokedex;
	}

	@Override
	public HeroClass getHeroClass() {
		return this.heroClass;
	}

	@Override
	public void pick(Lootable loot) throws NullPointerException {
		ExceptionThrower.checkNullPointer(loot);
		
		try {
			this.exp.addToValue(loot.getExp());
		} catch (IllegalStateException e) {
			levelUp();
		}
		
		this.gold += loot.getGold();
		loot.getItem().ifPresent(i -> this.inventory.addItem(i));
	}

	private void levelUp() {
		double previousLimit = this.exp.getLimit();
		this.exp = new SimpleLimitedCounter(0, previousLimit + 500);
		this.level ++;
		this.uppableParam.addPoint(Constants.STATPOINTS_ONLEVELUP);
		this.heroClass.getSkillTree().addPoint(Constants.SKILLPOINTS_ONLEVELUP);;
	}

	@Override
	public TargetPointSystem<Parameter> getUpgradableParameter() {
		return this.uppableParam;
	}

	@Override
	public BodyDef defBody() {
		return Box2DUtils.bodyDefBuilder()
					.type(BodyType.DYNAMIC)
					.build();
	}

	@Override
	public Collection<FixtureDef> defFixture() {
		Collection<FixtureDef> retValue = new LinkedList<>();
		Filter filter = Box2DUtils.filterBuilder()
						.addCategory(FilterType.HERO.getValue())
						.addMask(FilterType.GROUND.getValue())
						.addMask(FilterType.ENEMY.getValue())
						.addMask(FilterType.LOOTABLE.getValue())
						.addMask(FilterType.ENEMYATTACK.getValue())
						.build();
		PolygonShape underBody = new PolygonShape();
		float bodyX = Constants.HERO_SIZE.getX() * this.BODY_PROP.getX();
		float bodyY = Constants.HERO_SIZE.getY() * this.BODY_PROP.getY();
		underBody.setAsBox(bodyX, bodyY , new Vec2(0,-(Constants.HERO_SIZE.getY() / 2)), 0f);
		CircleShape head = new CircleShape();
		head.getVertex(0).set(0, (Constants.HERO_SIZE.getY() / 2));
		head.setRadius(Constants.HERO_SIZE.getX() * this.HEAD_PROP);
		retValue.add(Box2DUtils.fixDefBuilder().shape(underBody).friction(0).filter(filter).build());
		retValue.add(Box2DUtils.fixDefBuilder().shape(head).friction(0).filter(filter).build());
		return retValue;
	}

	
	@Override
	public Multimap<String, Slot> getEquippedItem() {
		return this.slots;
	}
	
}
