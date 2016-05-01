package hollowmen.model;

/**
 * This interface represents a strategy for change something to a {@link Character}
 * @author pigio
 *
 */
public interface TypeAction {
	
	/**
	 * This method will implemented and holds the change that
	 * this object will do on the {@code Character}
	 * @param subject {@link Character} that perform the action
	 */
	public void doAction(Character subject);
	
}
