package hollowmen.model;

/**
 * This interface represents a strategy for change something to a {@link Actor}
 * @author pigio
 *
 */
public interface TypeAction {
	
	/**
	 * This method will implemented and holds the change that
	 * this object will do on the {@code Character}
	 * @param subject {@link Actor} that perform the action
	 * @throws IllegalStateException if the {@code Character} can't do this action
	 * @throws NullPointerException
	 */
	public void doAction(Actor subject) throws IllegalStateException, NullPointerException;
	
}
