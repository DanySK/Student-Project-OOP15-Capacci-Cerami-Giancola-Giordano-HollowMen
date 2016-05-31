package hollowmen.model.roomentity.hero.skilltree;

import hollowmen.model.Information;
import hollowmen.model.Modifier;
import hollowmen.model.dungeon.DungeonSingleton;
import hollowmen.model.utils.Actors;

public class SkillNodeModifier extends SkillNodeImpl{

	
	private Modifier mod;
	
	public SkillNodeModifier(Information info, String tag, int level, double limit, Modifier mod) {
		super(info, tag, level, limit);
	}

	
	@Override
	public void addToValue(double value) throws IllegalArgumentException, IllegalStateException {
		super.addToValue(value);
		Actors.addModifier(DungeonSingleton.getInstance().getHero(), this.mod);
	}
	
	@Override
	public void subToValue(double value) throws IllegalArgumentException, IllegalStateException {
		super.addToValue(value);
		Actors.removeModifier(DungeonSingleton.getInstance().getHero(), this.mod);
	}
	
}
