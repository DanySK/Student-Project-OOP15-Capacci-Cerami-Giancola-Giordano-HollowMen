package hollowmen.enumerators;

/**
 * Contains the list of possible menu 
 * 
 * @author Giordo
 *
 */
public enum InputMenu {
	MAIN("main"),
	CLASS("class"),
	DIFFICULTY("difficulty"),
	HELP("help"),
	PAUSE("pause"),
	INVENTORY("inventory"),
	SKILL_TREE("skillTree"),
	POKEDEX("pokedex"),
	SHOP("shop"),
	ACHIEVEMENTS("achievements");
	
	private String s;
	
	private InputMenu(String s){
		this.s=s;
	}
	
	/**
	 * @return String that represent the name of the menu
	 */
	public String getString(){
		return this.s;
	}
}
