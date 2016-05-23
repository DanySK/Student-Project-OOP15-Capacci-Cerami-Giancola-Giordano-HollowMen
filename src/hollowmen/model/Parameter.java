package hollowmen.model;

/**
 * This interface represent a {@code Parameter} intended as a {@code double} value
 * with a name associated inherited by {@link Information}<br>
 * <br>
 * A {@code Parameter} can collect and remove {@link Modifier}
 * @author pigio
 *
 */
public interface Parameter extends InformationUser{
	
	/**
	 * This method give the final value for this {@code Parameter}
	 * @return {@code double} value
	 */
	public double getValue();
	
	/**
	 * This method <u>add</u> a {@link Modifier} to the Character
	 * @param mod {@link Modifier}
	 */
	public void addModifier(Modifier mod) throws NullPointerException;
	
	/**
	 * This method <u>remove</u> a {@link Modifier} to the Character
	 * @param mod {@link Modifier}
	 * @throws IllegalArgumentException If the Character hasn't the passed {@link Modifier}
	 * @throws NullPointerException
	 */
	public void removeModifier(Modifier mod) throws IllegalArgumentException, NullPointerException;
}
