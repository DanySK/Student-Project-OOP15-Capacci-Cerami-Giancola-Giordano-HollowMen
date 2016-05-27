package hollowmen.model;

import java.util.function.Consumer;

/**
 * This interface represents the time inside the game,
 * since there are many time-dependent-event internally <br>
 * This time is updated each time adding the <b>deltaTime</b> each time {@code update(long deltaTime)}
 * is called in {@link Dungeon}
 * @author pigio
 *
 */
public interface Time  extends LimitedCounter{

	/**
	 * This method adds the given <b>event</b> to this {@code Time} and when
	 * its duration finished {@code trigger()} will be invoked 
	 * @param event {@link TimeEvent}
	 */
	public <T> void register(T subj, double duration, Consumer<T> action);
}
