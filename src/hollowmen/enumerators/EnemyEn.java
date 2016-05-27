package hollowmen.enumerators;

public class EnemyEn {

	public static enum Title {
		BOSS,
		COMMANDER,
		ORDINARY;
		
		@Override
		public String toString(){
			return this.name().toLowerCase();
		}
	}
	
	public static enum Habitat {
		GROUND,
		FLY;
		
		@Override
		public String toString(){
			return this.name().toLowerCase();
		}
	}
	
	public static enum Name {
		SLIME,
		BAT,
		PUPPET;
		
		@Override
		public String toString(){
			return this.name().toLowerCase();
		}
	}
	
}
