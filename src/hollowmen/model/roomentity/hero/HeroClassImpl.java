package hollowmen.model.roomentity.hero;

import java.util.Collection;
import java.util.Collections;

import hollowmen.model.Challenge;
import hollowmen.model.HeroClass;
import hollowmen.model.Information;
import hollowmen.model.Parameter;
import hollowmen.model.SkillTree;

public class HeroClassImpl implements HeroClass{

	private Information info;
	
	private SkillTree skillTree;
	
	private Collection<Parameter> param;
	
	private Challenge challenge;
	
	
	public HeroClassImpl(Information info, SkillTree skillTree, Collection<Parameter> param, Challenge challenge) {
		this.info = info;
		this.skillTree = skillTree;
		this.param = param;
		this.challenge = challenge;
	}

	@Override
	public Information getInfo() {
		return this.info;
	}

	@Override
	public SkillTree getSkillTree() {
		return this.skillTree;
	}

	@Override
	public Collection<Parameter> getBaseParam() {
		return Collections.unmodifiableCollection(this.param);
	}

	@Override
	public Challenge getChallenge() {
		return this.challenge;
	}

	
}
