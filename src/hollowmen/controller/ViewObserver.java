package hollowmen.controller;

import hollowmen.model.Information;

/**
 * Interface used to notify controller about input
 * 
 * @author Giordo
 *
 */
public interface ViewObserver {
	
	/**
	 * The method {@code addInput} notify a simple input
	 * 
	 * @param command
	 */
	public void addInput(String command);
	
	/**
	 * The method {@code addInput} notify an input and the 
	 * {@link Information} on which the input is used
	 * 
	 * @param command
	 * @param info
	 */
	public void addInput(String command, Information info);
}
