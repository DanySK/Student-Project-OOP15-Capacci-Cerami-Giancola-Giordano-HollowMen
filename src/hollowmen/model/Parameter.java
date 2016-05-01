package hollowmen.model;

/**
 * This interface represent a {@code Parameter} intended as a {@code double} value
 * with a name associated inherited by {@link Information}<br>
 * <br>
 * A {@code Parameter} can be affected by {@link Modifier} only through the {@link Character}
 * @author pigio
 *
 */
public interface Parameter extends Information{
	
	/**
	 * This method give the base value for this {@code Parameter}
	 * @return {@code double} value
	 */
	public double getValue();
	
}
