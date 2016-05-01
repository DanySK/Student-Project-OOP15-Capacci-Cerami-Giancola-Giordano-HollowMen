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
	 * This method give all the {@code SkillBranch} from this tree
	 * @return {@link Collection}<{@link SkillBranch}>
	 */
	public Collection<SkillBranch> getSkillBranch();
	
	/**
	 * This method add a skill point on the <b>node</b>
	 * @param node {@link SkillNode} where add a point
	 * @throws IllegalStateException If the <b>node</b> isn't available
	 * @throws NullPointerException
	 */
	public void spendSkillPointOn(SkillNode node) throws IllegalStateException, NullPointerException;
	
	/**
	 * This method give a {@code int} represents how many points are unspent
	 * @return {@code int} how many points are unspent
	 */
	public int getUnspentSkillPoint();
	
	/**
	 * This method retrieve all the spent points from the {@code SkillNode}s
	 * all the points are now unspent and can be respent
	 */
	public void reset();
	
}
