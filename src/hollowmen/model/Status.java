package hollowmen.model;

/**
 * This interface represents a {@link Time}-dependent betterment or worsening affecting
 * a {@link Actor}
 * @author pigio
 *
 */
public interface Status extends InformationUser{

	/**
	 * This method attaches this {@code Status} to 
	 * @param actor
	 */
	public void attachTo(Actor actor);
}
