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
	 * This method adds the given <b>time</b> simulating the flow of time
	 * @param time 
	 */
	public void addTime(long time);
	
	/**
	 * This method adds the given <b>event</b> to this {@code Time} and when
	 * its duration finished {@code trigger()} will be invoked 
	 * @param event {@link TimeEvent}
	 */
	public void register(TimeEvent event);
}
