package hollowmen.model.roomentity.hero;

import java.awt.Rectangle;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import hollowmen.model.roomentity.ActionAllowed;
import hollowmen.model.roomentity.ActorAbs;
import hollowmen.model.utils.Constants;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

public class HeroImpl extends ActorAbs implements Hero{

	private LimitedCounter exp;
	
	private int level;
	
	private int gold;
	
	private Inventory inventory;
	
	private Pokedex pokedex;
	
	private HeroClass heroClass;
	
	private TargetPointSystem<Parameter> uppableParam;
	
	private Map<String, List<Slot>> slots;
	
	public HeroImpl(Information info, Rectangle size, int ID, ActionAllowed aA, Collection<Parameter> param) {
		super(info, size, ID, aA, param);
		// TODO Auto-generated constructor stub
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
				item.getModifiers().stream()
					.forEach(m -> super.addModifier(m));
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
				temp.getModifiers().stream()
					.forEach(m -> super.removeModifier(m));
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
		//int previousLimit = this.exp.getLimit()
		//this.exp = new LimitedValueImpl(0, previousLimit);
		this.level ++;
		this.uppableParam.addPoint(Constants.STATPOINTS_ONLEVELUP);
		this.heroClass.getSkillTree().addPoint(Constants.SKILLPOINTS_ONLEVELUP);;
	}

	@Override
	public TargetPointSystem<Parameter> getUpgradableParameter() {
		return this.uppableParam;
	}


}
