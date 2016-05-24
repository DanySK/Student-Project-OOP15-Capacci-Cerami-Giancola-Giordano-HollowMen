package hollowmen.model;

/**
 * This interface represents any value that has a upper limit that can't exceed<br>
 * @author pigio
 *
 * @param <T> anything that extends {@link Number}
 */
public interface LimitedValue<T extends Number> {
	
	/**
	 * This method give the current value
	 * @return T value
	 */
	public T getValue();
	
	/**
	 * This method give the limit the value can't exceed
	 * @return T limit
	 */
	public T getLimit();
	
	/**
	 * This method add <b>value</b> to current value
	 * @param value
	 * @throws IllegalArgumentException If <b>value</b> less than 0
	 * @throws IllegalStateException If the limit is reached
	 */
	public void addToValue(T value) throws IllegalArgumentException, IllegalStateException;
	
	/**
	 * This method subtract <b>value</b> to current value
	 * @param value
	 * @throws IllegalArgumentException If <b>value</b> less than 0
	 * @throws IllegalStateException If current value sub <b>value</b> would become negative
	 */
	public void subToValue(T value) throws IllegalArgumentException, IllegalStateException;
}
