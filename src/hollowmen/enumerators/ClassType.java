package hollowmen.enumerators;

/**
 * Contains the list of the game class.
 * 
 * @author Giordo
 *
 */
public enum ClassType {
	WARRIOR,
	ASSASSIN,
	MAGE;
	
	@Override
	public String toString(){
		return this.name().toLowerCase();
	}
}
