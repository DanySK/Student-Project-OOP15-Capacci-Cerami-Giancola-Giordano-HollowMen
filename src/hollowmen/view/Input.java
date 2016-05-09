package hollowmen.view;

import hollowmen.model.Information;

/**
 * The {@code Input} interface represents the input
 * attached to a button.
 * 
 * @author Juls
 *
 */
public interface Input {
	
	/**
	 * The {@code getAction()} method gets the corresponding
	 * string from the button that has been clicked.
	 * 
	 * @return a {@code String} that represents the meaning of the button.
	 */
	public String getAction();
	
	/**
	 * The {@code isOperational()} method tells if the Input Event
	 * must be consumed on an Object. For instance, the "Equip" action
	 * must be called on an Item.
	 * 
	 * @return {@code true} If the action must be called on an Object.
	 */
	public boolean isOperational();
	
	/**
	 * The {@code getSubjectOperation()} method returns the name
	 * corresponding to the Object on which the Action must be consumed.
	 * 
	 * @return {@link hollowmen.model.Information} of the Object 
	 * @throws IllegalStateException If {@code isOperational()} returns {@code false}
	 */
	public Information getSubjectOperation() throws IllegalStateException;
	
	

}
