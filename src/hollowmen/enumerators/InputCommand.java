package hollowmen.enumerators;

/**
 * Contains the list of possible command from the player
 * 
 * @author Giordo
 *
 */
public enum InputCommand {
	
	ABILITY1("ability1"),
	ABILITY2("ability2"),
	ABILITY3("ability3"),
	ATTACK("attack"),
	BACK("back"),
	CONSUMABLE("consumable"),
	EQUIP("equip"),
	INTERACT("interact"),
	JUMP("jump"),
	LEFT("left"),
	RIGHT("right"),
	TRADE("trade");
	
	private String s;
	
	private InputCommand(String s){
		this.s=s;
	}
	
	/**
	 * @return String that represent the command
	 */
	public String getString(){
		return this.s;
	}
}
