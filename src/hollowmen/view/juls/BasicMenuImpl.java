package hollowmen.view.juls;

public class BasicMenuImpl implements BasicMenu {

	public BasicMenuImpl() {
		
	}
	
	@Override
	public void drawBasicMenu(MenuType name) {
		switch (name) {
		
		case MAIN:
			new MainMenu();
			break;
			
		case CLASS:
		//	new ClassChoice();
			break;
			
		case DIFFICULTY:
		//	new Difficulty();
			break;
			
		case HELP:
		//	new Help();
			break;
			
		case PAUSE:
		//	new Pause();
			break;
			
		default:
			break;
		
		}
		
	}

}
