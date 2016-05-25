package hollowmen.model.item;

import java.util.Collection;

import hollowmen.model.Information;
import hollowmen.model.Item;
import hollowmen.model.Modifier;
import hollowmen.utilities.ExceptionThrower;

/**
 * This class is a first implementation of {@link Item}
 * For instantiate this class use the static {@code builder()} method which return a {@link ItemBuilder}
 * @author pigio
 *
 */
public class ItemImpl implements Item{

	private Information info;
	
	private ItemState state;
	
	private Collection<Modifier> mod;
	
	private int goldValue;
	
	private int rarity;
	
	private String slot;
	
	private String heroClassEquippable;
	
	private ItemImpl(Information info, ItemState state, Collection<Modifier> mod, int sellValue, int rarity,
			String slot, String heroClassEquippable) {
		this.info = info;
		this.state = state;
		this.mod = mod;
		this.goldValue = sellValue;
		this.rarity = rarity;
		this.slot = slot;
		this.heroClassEquippable = heroClassEquippable;
	}

	//Per permettere alla inner class di essere instanziata senza problemi
	private ItemImpl() {};
	
	//bariamo sul fatto che sembra il costruttore del Builder, in questo modo riduciamo la
	//sua visibilità da ItemImpl e ItemBuilder a ItemBuilder soltanto
	/**
	 * This method give an {@code ItemBuilder} that is the only way to get an instance
	 * @return {@link ItemBuilder}
	 */
	public static ItemBuilder builder() {
		return new Builder();
	}
	


	private static class Builder extends ItemImpl implements ItemBuilder {

		
		@Override
		public ItemBuilder info(Information info) {
			super.info = info;
			return this;
		}

		@Override
		public ItemBuilder state(ItemState state) {
			super.state = state;
			return this;
		}

		@Override
		public ItemBuilder modifier(Collection<Modifier> coll) {
			super.mod = coll;
			return this;
		}

		@Override
		public ItemBuilder value(int i) {
			super.goldValue = i;
			return this;
		}

		@Override
		public ItemBuilder rarity(int i) {
			super.rarity = i;
			return this;
		}

		@Override
		public ItemBuilder slot(String slot) {
			super.slot = slot;
			return this;
		}

		@Override
		public ItemBuilder heroClass(String className) {
			super.heroClassEquippable = className;
			return this;
		}

		@Override
		public Item build() throws IllegalStateException{
			ExceptionThrower.checkNullPointer(super.getInfo());
			ExceptionThrower.checkNullPointer(super.getState());
			ExceptionThrower.checkNullPointer(super.getModifiers());
			ExceptionThrower.checkNullPointer(super.getSlot());
			ExceptionThrower.checkNullPointer(super.getHeroClassEquippable());
			ExceptionThrower.checkIllegalState(super.getInfo(), i -> !i.getDescription().isPresent());
//			ExceptionThrower.checkIllegalState(super.getModifiers(), m -> m.isEmpty());
			ExceptionThrower.checkIllegalState(super.getRarity(), i -> i <= 0);
			ExceptionThrower.checkIllegalState(super.getGoldValue(), i -> i <= 0);
			
			return new ItemImpl(super.getInfo(), 
					super.getState(), super.getModifiers(), super.getGoldValue(), 
					super.getRarity(), super.getSlot(), super.getHeroClassEquippable());
		}
		
	}
	
	@Override
	public Information getInfo() {
		return this.info;
	}

	@Override
	public ItemState getState() {
		return this.state;
	}
	
	@Override
	public void setState(ItemState state) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Collection<Modifier> getModifiers() {
		return this.mod;
	}

	@Override
	public int getGoldValue() {
		return this.goldValue;
	} 

	@Override
	public int getRarity() {
		return this.rarity;
	}

	@Override
	public String getSlot() {
		return this.slot;
	}

	@Override
	public String getHeroClassEquippable() {
		return this.heroClassEquippable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((heroClassEquippable == null) ? 0 : heroClassEquippable.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + rarity;
		result = prime * result + goldValue;
		result = prime * result + ((slot == null) ? 0 : slot.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		ItemImpl other = (ItemImpl) obj;
		if (heroClassEquippable == null) {
			if (other.heroClassEquippable != null)
				return false;
		} else if (!heroClassEquippable.equals(other.heroClassEquippable))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (rarity != other.rarity)
			return false;
		if (slot == null) {
			if (other.slot != null)
				return false;
		} else if (!slot.equals(other.slot))
			return false;
		return true;
	}
	
}
