package hollowmen.model.roomentity.hero.skilltree;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import hollowmen.model.SkillNode;
import hollowmen.model.SkillTree;
import hollowmen.model.utils.Constants;
import hollowmen.model.utils.Counter;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

public class SkillTreeImpl implements SkillTree{
	
	private int skillPointUnspent;
		
	private Map<Pair<String, Integer>, Collection<SkillNode>> branchLevelNodes;
	
	//unlocked SkillNode
	private Map<String, SkillNode> allTargets;
	
	//the sum of all the Counter value in a branch
	private Map<String, Counter> pointsOnBranch;
	
	//the sum of all skill point for the branch in the level
	private Map<Pair<String, Integer>, Counter> pointsOnBranchLevel;
	
	@Override
	public Collection<SkillNode> getTargets() {
		return this.allTargets.entrySet().stream()
				.map(e -> e.getValue())
				.collect(Collectors.toList());
	}

	@Override
	public int getUnspentPoint() {
		return this.skillPointUnspent;
	}

	@Override
	public void addPoint(int pointToAdd) {
		this.skillPointUnspent += pointToAdd;
	}

	
	@Override
	public Collection<SkillNode> getSkillNodes() {
		List<SkillNode> list = new LinkedList<>();
		this.branchLevelNodes.entrySet().stream()
				.forEach(e -> e.getValue().stream()
						.forEach(o -> list.add(o)));
		return list;	
	}

	
	@Override
	public void retrievePointFrom(SkillNode target) throws IllegalArgumentException {
		ExceptionThrower.checkIllegalArgument(target, t -> this.allTargets.containsKey(t));
		retrieveUpdateRoutine(target, 1);
		checkMaxValueIntegrity(target.getTag(), 1);
		checkTreeIntegrity(target);
	}
	
	private void retrieveUpdateRoutine(SkillNode target, int value) {
		this.spendUpdateRoutine(target, -value);
	}
	
	
	private void checkMaxValueIntegrity(String tag, int change) {
		int previousLevel = (this.pointsOnBranch.get(tag).getCount() + change) / Constants.SKILLPOINTSFORUPGRADE;
		if(previousLevel > this.maxLevelForTag(tag)) {
			this.removeTargetLevel(keyGen(tag, previousLevel));
		}
	}

	

	//Check if from the target below the sum of all previous skill points spent
	//(target included) are more than the skillpoints to unlock the next level
	//eventually remove all the nodes up target
	private void checkTreeIntegrity(SkillNode target) {
		int sumPreviousNodePoints = this.pointsOnBranchLevel.entrySet().stream()
				.filter(x -> x.getKey().getY() <= target.getLevel())
				.map(x -> x.getKey().getY())
				.reduce((x,y) -> x + y).orElse(0);
		if(sumPreviousNodePoints < (target.getLevel()+1) * Constants.SKILLPOINTSFORUPGRADE ) {
			this.removeAllPointsFromLevelToLimit(keyGen(target));
		}
		
	}
		
	//This will remove all points from the level (NOT included) give to the end
	private void removeAllPointsFromLevelToLimit(Pair<String, Integer> levelToRemove) {
		this.branchLevelNodes.entrySet().stream()
			.filter(e -> e.getKey().getY() > levelToRemove.getY())
			.map(e -> e.getKey())
			.forEach(x -> this.removeTargetLevel(x));
	}
	
	//This will remove the target level, retrieving all his points and
	//removing all this level SkillNode from the allTargets Map
	private void removeTargetLevel(Pair<String, Integer> levelToRemove) {
		this.branchLevelNodes.get(levelToRemove).stream()
			.forEach(s -> {
				if(this.allTargets.containsKey(s.getInfo().getName())){
					this.retrieveUpdateRoutine(s, (int) s.getValue());
					this.allTargets.remove(s.getInfo().getName());
				}
			});
	}
	
	@Override
	public void spendPointOn(SkillNode target) throws IllegalStateException, IllegalArgumentException {
		ExceptionThrower.checkIllegalArgument(target, t -> this.allTargets.containsKey(t));
		ExceptionThrower.checkIllegalState(this, s -> s.getUnspentPoint() > 0);
		spendUpdateRoutine(target, 1);
		checkNewTargets(target);
	}
	
	private void spendUpdateRoutine(SkillNode target, int value) {
		this.allTargets.get(target.getInfo().getName()).addToValue(value);
		this.pointsOnBranchLevel.get(keyGen(target)).add(value);;
		this.pointsOnBranch.get(target.getTag()).add(value);;
		this.skillPointUnspent-= value;
	}
	
	
	private void checkNewTargets(SkillNode target) {
		if(this.pointsOnBranch.get(target.getTag()).getCount() % Constants.SKILLPOINTSFORUPGRADE == 0) {
			int levelToUnlock = this.maxLevelForTag(target.getTag());
			this.branchLevelNodes.get(keyGen(target.getTag(), levelToUnlock)).stream()
				.forEach(s -> allTargets.put(s.getInfo().getName(), s));
		}
	}
	
	private int maxLevelForTag(String tag) {
		return this.pointsOnBranch.get(tag).getCount() / Constants.SKILLPOINTSFORUPGRADE;
	}
	
	private Pair<String, Integer> keyGen(String s, Integer i) {
		return new Pair<>(s, i);
	}
	
	private Pair<String, Integer> keyGen(SkillNode sN) {
		return this.keyGen(sN.getTag(), sN.getLevel());
	}
	
}
