package hollowmen.model;

public interface TimeEvent {

	/**
	 * This method gives how much time this {@code TimeEvent} will stay
	 * @return {@code double} how much time this will stay
	 */
	public double getDuration();
	
	/**
	 * This method trigger this {@code TimeEvent} doing something based on implementation
	 */
	public void trigger();
}
