package hollowmen.model;

import hollowmen.model.utils.GameOverException;
import hollowmen.utilities.Pair;

/**
 * A {@code Dungeon} has a sequence of {@link Floor}, a single {@code Floor} can be selected if it's unlocked.
 * For unlock a {@code Floor} the player has to complete the previous {@code Floor}.<br>
 * <br>
 * A {@code Dungeon} has a {@link Difficulty} which influence some parameter in the game
 * @author pigio
 *
 */
public interface Dungeon {

	/**
	 * This enum indicates the possible value of challenge, 
	 * their value will be used for modify the {@code Enemy} and their loot (less Exp and Gold).<br>
	 * <br>
	 * EASY, NORMAL, HARD.
	 * @author pigio
	 *
	 */
	public enum Difficulty{
		EASY,
		NORMAL,
		HARD;
	}
	
	/**
	 * This method handle the update cycle that can be summarized as:<br>
	 * move all Entities,<br>
	 * check Collision,<br>
	 * resolve Collision.
	 * 
	 * @param deltaTime the time elapsed from the last update.
	 * @throws GameOverException If the time is over or If Hero's death
	 */
	public void update(long deltaTime) throws GameOverException;

	/**
	 * This method set the {@code Floor} where the player will play
	 * 
	 * @param floorNumber where the player will play
	 * @throws IllegalStateException If the {@code Floor} is locked
	 * @throws NullPointerException
	 */
	public void goTo(int floorNumber) throws IllegalStateException, NullPointerException;
	
	/**
	 * @return {@link Pair} X floor's number reached, Y max floor's number
	 */
	public Pair<Integer, Integer> getFloors();
	
	/**
	 * This method return the current {@code Floor} selected
	 * @return {@link Floor} current selected
	 */
	public Floor getCurrentFloor();
	
	/**
	 * This method will set the current {@code Floor}'s number to 0,
	 * this {@code Floor} has the lobby {@code Room}<br>
	 * Increase the reached floor's number if this method is called when the {@code Hero}
	 * pass the last {@code Room} of the current {@code Floor}
	 */
	public void endRun();
	/**
	 * This method give the currently setted {@code Difficulty} for the {@code Dungeon}
	 * @return {@link Difficulty} currently setted
	 */
	public Difficulty getDifficulty();
	
	/**
	 * This method give the {@code Dungeons}'s {@code Shop}
	 * @return {@link Shop}
	 */
	public Shop getShop();
	
}
