package hollowmen.view;

import hollowmen.model.Information;

/**
 * The Abstract Class {@code SimpleInput} will be extended if the
 * Input is "simple", so if it immediately consumes the event.
 * @author Juls
 *
 */
public abstract class SimpleInput implements Input {
	
	/**
	 * The {@code isOperatinal()} method in the Abstract Class {@link SimpleInput}
	 * must return false because this class only allows operation on just one button.
	 * 
	 * @return {@code false} 
	 * 
	 */
	public boolean isOperational() {
		return false;
	}
	
	/**
	 * The {@code getSubjectOperation()} in the Abstract Class {@link SimpleInput}
	 * can only throw an exception, according to the {@link Input} Interface.
	 * 
	 * @throws IllegalStateException
	 */
	public Information getSubjectOperation() throws IllegalStateException {
		throw new IllegalStateException();
	}

}
