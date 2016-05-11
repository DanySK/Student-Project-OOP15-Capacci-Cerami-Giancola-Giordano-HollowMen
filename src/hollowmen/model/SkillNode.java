package hollowmen.model;

/**
 * This interface represents an object that will modify something in the game
 * based on the point spend on it
 * @author pigio
 *
 */
public interface SkillNode extends InformationUser{
	
	/**
	 * This method give how many point are spent on this {@code SkillNode}
	 * @return {@code int}
	 */
	public int getSkillPointSpent();
	
	/**
	 * This method give how many points can be spent on this {@code SkillNode}
	 * @return {@code int}
	 */
	public int getMaxSkillPoint();
	
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
}
