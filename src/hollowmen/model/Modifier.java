package hollowmen.model;

import java.util.function.BinaryOperator;

/**
 * This interface represents an object that can modify the value of a {@link Parameter},
 * it holds the strategy for modify
 * @author pigio
 *
 */
public interface Modifier {
	
	/**
	 * This method give the target {@code Parameter} and the value
	 * @return {@link Parameter} target
	 */
	public Parameter getParameter();
	
	/**
	 * This method give the strategy for modify the target {@code Parameter}
	 * @return {@link BinaryOperator}{@code <Double>} what operation will modify the target {@link Parameter}
	 */
	public BinaryOperator<Double> getOperation();
}
