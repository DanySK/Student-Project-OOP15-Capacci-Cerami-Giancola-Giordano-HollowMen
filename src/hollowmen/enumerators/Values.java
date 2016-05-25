package hollowmen.enumerators;

public enum Values {
	
	LIFE,
	MAXLIFE,
	EXP,
	EXPNEEDE,
	TIMER,
	GOLD,
	LEVEL,
	FLOOR;
	
	private int value=0;
	
	public void setValue(int v){
		this.value=v;
	}
	
	public int getValue(){
		return this.value;
	}
}
