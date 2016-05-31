package hollowmen.model.dungeon;

public enum FilterType {
	
	GROUND(0x0001),
	HERO(0x0002),
	ENEMY(0x0004),
	LOOTABLE(0x0008),
	HEROATTACK(0x0010),
	ENEMYATTACK(0x0020),
	WALL(0x0040),
	FLY(0x0080),
	FLYLINE(0x0100);
	
	private int value;
	
	
	private FilterType(int i) {
		value = i;
	}
	
	public int getValue() {
		return this.value;
	}
}
