package hollowmen.model;

import java.util.Collection;

/**
 * This interface represent an object that has {@link SkillBranch} and
 * can spend point on the {@link SkillNode}
 * @author pigio
 *
 */
public interface SkillTree {

	/**
	 * This method gives all the {@code SkillBranch} from this tree
	 * @return {@link Collection}<{@link SkillBranch}>
	 */
	public Collection<SkillBranch> getSkillBranch();
	
	/**
	 * This method gives the {@code TargetPointSystem} based on this {@code SkillTree}'s
	 * {@code SkillNode}
	 * @return {@link TargetPointSystem}<{@link SkillNode}>
	 */
	public TargetPointSystem<SkillNode> getUpgradableNodes();
	
}
