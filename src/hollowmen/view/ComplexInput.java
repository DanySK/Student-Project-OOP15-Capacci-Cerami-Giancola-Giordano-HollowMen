package hollowmen.view;

/**
 * The Abstract Class {@code ComplexInput} will be extended if the Input
 * must be consumed on a different Object, for instance on an Item.
 * @author Juls
 *
 */
public abstract class ComplexInput implements Input {
	
	/**
	 * The {@code isOperational()} method in the Abstract Class {@link ComplexInput}
	 * can only return {@code true}, according to the {@link Input} interface.
	 * 
	 * @return {@code true}
	 */
	public boolean isOperational() {
		return true;
	}

}
