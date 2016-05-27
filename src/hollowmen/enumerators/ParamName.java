package hollowmen.enumerators;

public enum ParamName {

	ATTACK,
	DEFENSE,
	HP,
	ATTACKSPEED,
	ATTACKRANGE,
	MOVSPEED,
	DURATION,
	COOLDOWN;
	
	@Override
	public String toString(){
		return this.name().toLowerCase();
	}
	
}
