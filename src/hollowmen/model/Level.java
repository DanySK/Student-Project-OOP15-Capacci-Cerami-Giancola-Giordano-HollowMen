package hollowmen.model;

/**
 * This interface represent {@code Level} intended as an Object that hold the strategy for the "level up"<br>
 * It stores exp and when it reaches the maxExp, the level is increased by one
 * and a new maxExp is setted
 * @author pigio
 *
 */
public interface Level {
	
	/**
	 * This method add the <b>exp</b> at the current level<br>
	 * @param exp {@code int} value to add
	 */
	public void addExp(int exp);
	
	/**
	 * This method give the current level
	 * @return {@code int} represents the current level
	 */
	public int getLevel();
	
	/**
	 * This method give the exp accumulate for the current level
	 * @return {@code int} represents the exp accumulate for the current level
	 */
	public int getCurrentExp();
	
	/**
	 * This method give the max exp storable for the current level
	 * @return {@code int} represents the max exp storable for the current level
	 */
	public int getCurrentExpMax();
}
