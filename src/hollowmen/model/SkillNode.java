package hollowmen.model;

/**
 * This interface represents an object that will modify something in the game
 * based on the point spend on it
 * @author pigio
 *
 */
public interface SkillNode extends InformationUser{
	
	/**
	 * This method check the availability of this {@code SkillNode}
	 * @return {@code true} if there are enough skill point in this 
	 * {@link SkillBranch}, {@code false} otherwise
	 */
	public boolean isAvaiable();
	
	/**
	 * This method give the level of this {@code SkillNode}
	 * @return {@code int}
	 */
	public int getLevel();
	
	/**
	 * This method give {@code LimitedValue} which value is the skill points
	 * spent on this node and limit the max skill points to spend on this node 
	 * @return {@link LimitedValue}
	 */
	public LimitedValue<Integer> getPointsSpent();
}
