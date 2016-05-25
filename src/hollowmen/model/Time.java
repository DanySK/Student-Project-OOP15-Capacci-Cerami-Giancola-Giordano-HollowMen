package hollowmen.model;

/**
 * This interface represents the time inside the game,
 * since there are many time-dependent-event internally <br>
 * This time is updated each time adding the <b>deltaTime</b> each time {@code update(long deltaTime)}
 * is called in {@link Dungeon}
 * @author pigio
 *
 */
public interface Time {

	/**
	 * This method gives a {@code LimitedValue} which value is the time passed
	 * and limit is the max time until game over
	 * @return {@link LimitedValue}
	 */
	public LimitedValue<Long> getTimeProgress();
	
	/**
	 * This method adds the given <b>event</b> to this {@code Time} and when
	 * its duration finished {@code trigger()} will be invoked 
	 * @param event {@link TimeEvent}
	 */
	public void register(TimeEvent event);
}
