package hollowmen.enumerators;

/**
 * enum used to identify game difficulty
 * 
 * @author Giordo
 *
 */
public enum Difficulty {
	NORMAL(1),
	HARD(2),
	IMPOSSIBLE(3);
	
	private int v;
	
	private Difficulty(int v){
		this.v=v;
	}
	
	/**
	 * @return int value that represent the game difficulty
	 */
	public int getValue(){
		return this.v;
	}
}
