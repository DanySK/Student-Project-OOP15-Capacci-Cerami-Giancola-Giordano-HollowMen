package hollowmen.model.enemy;

public class EnemyFactory {

	private EnemyFactory() {};
	
	private static class Holder {
		public static EnemyFactory INSTANCE = new EnemyFactory();
	}
	
	public static EnemyFactory getInstance() {
		return Holder.INSTANCE;
	}
	
	
	public EnemyBuilder getBuilderFor(String s) {
		switch(s) {
		case "slime":
			return new Slime.Builder();
		case "bat":
			return new Bat.Builder();
		case "puppet":
			return new Puppet.Builder();
		}
		return null;
	}
}
