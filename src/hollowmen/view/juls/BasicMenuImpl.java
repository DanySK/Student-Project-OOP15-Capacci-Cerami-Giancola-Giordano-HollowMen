package hollowmen.view.juls;

import hollowmen.enumerators.InputMenu;

/**
 * The {@code BasicMenuImpl} class represents the concrete
 * implementation of {@link BasicMenu}. It allows to draw "basic" menus on screen.
 * @author Juls
 */
public class BasicMenuImpl implements BasicMenu {

	public BasicMenuImpl() {}
	
	@Override
	public void drawBasicMenu(InputMenu name) {
		switch (name) {
		
		case MAIN:
			new MainMenu();
			break;
			
		case CLASS:
		//	new ClassChoice();
			break;
			
		case DIFFICULTY:
		//	new DifficultyMenu(frame);
			break;
			
		case HELP:
		//	new HelpMenu(frame);
			break;
			
		case PAUSE:
		//	new PauseMenu(frame);
			break;
			
		default:
			break;
		
		}	
	}
}
