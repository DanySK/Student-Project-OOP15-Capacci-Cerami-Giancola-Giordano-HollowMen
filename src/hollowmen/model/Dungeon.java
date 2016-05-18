package hollowmen.model;

import java.util.Collection;

import hollowmen.utilities.Pair;

/**
 * This interface represents the {@code Dungeon} intended as the Entry Point of the Model.<br>
 * <br>
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
	 */
	public void update(long deltaTime);
	
	/**
	 * This method give a reference to {@code Hero}
	 * 
	 * @return {@link Hero}
	 */
	public Hero getHero();
	
	/**
	 * This method give a {@code Colelction} of {@code Pair} represent the number of the
	 * {@code Floor} and his avaiability
	 * 
	 * @return {@link Collection}<{@link Pair}<{@code Integer}, {@code Boolean}>>
	 */
	public Collection<Pair<Integer, Boolean>> getFloorsNumber();
	
	/**
	 * This method set the {@code Floor} where the player will play
	 * 
	 * @param floorNumber where the player will play
	 * @throws IllegalStateException If the {@code Floor} is locked
	 * @throws NullPointerException
	 */
	public void goTo(int floorNumber) throws IllegalStateException, NullPointerException;
	
	/**
	 * This method return the current {@code Floor} selected
	 * @return {@link Floor} current selected
	 */
	public Floor getCurrentFloor();
	
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
