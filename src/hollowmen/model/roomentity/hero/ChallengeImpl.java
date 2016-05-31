package hollowmen.model.roomentity.hero;

import java.util.Collection;
import java.util.LinkedList;

import hollowmen.model.Achievement;
import hollowmen.model.Challenge;

public class ChallengeImpl implements Challenge{

	private Collection<Achievement> achieve;
	
	
	public ChallengeImpl() {
		this.achieve = new LinkedList<>();
	}
	
	@Override
	public Collection<Achievement> getAchievements() {
		return this.achieve;
	}

}
