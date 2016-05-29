package hollowmen.model.enemy;

import hollowmen.enumerators.RoomEntityName;

public class EnemyFactory {

	private EnemyFactory() {};
	
	private static class Holder {
		public static EnemyFactory INSTANCE = new EnemyFactory();
	}
	
	public static EnemyFactory getInstance() {
		return Holder.INSTANCE;
	}
	
	
	@SuppressWarnings("incomplete-switch")
	public EnemyBuilder getBuilderFor(String s) {
		RoomEntityName en = RoomEntityName.valueOf(s);
		switch(en) {
		case SLIME:
			return new Slime.Builder();
		case BAT:
			return new Bat.Builder();
		case PUPPET:
			return new Puppet.Builder();
		}
		return null;
	}
}
