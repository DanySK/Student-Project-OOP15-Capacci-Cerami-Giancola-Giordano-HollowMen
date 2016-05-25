package hollowmen.model;

/**
 * This exception is thrown to alert someone outside the Model
 * @author pigio
 *
 */
public class GameOverException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7258974978201721791L;
	
	public GameOverException(final String reason) {
		super(reason);
	}
}
