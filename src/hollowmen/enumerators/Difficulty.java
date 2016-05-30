package hollowmen.enumerators;

/**
 * enum used to identify game difficulty
 * 
 * @author Giordo
 *
 */
public enum Difficulty {
	EASY(1),
	NORMAL(2),
	HARD(3);
	
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
