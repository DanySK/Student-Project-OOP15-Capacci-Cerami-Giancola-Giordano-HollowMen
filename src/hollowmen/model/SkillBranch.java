package hollowmen.model;

import java.util.Collection;

/**
 * This interface represents a container for the {@link SkillNode},
 * it also decided when a {@code SkillNode} is unlocked based 
 * on the skill poin spent on the branch 
 * @author pigio
 *
 */
public interface SkillBranch {

	/**
	 * This method give how many points are spent in this {@code SkillBranch}
	 * @return {@code int}
	 */
	public int getSkillPointSpentHere();
	
	/**
	 * This method give all the {@code SkillNode} in this {@code SkillBranch}
	 * @return {@link Collection}<{@link SkillNode}>
	 */
	Collection<SkillNode> getSkillNodes();
	
}
