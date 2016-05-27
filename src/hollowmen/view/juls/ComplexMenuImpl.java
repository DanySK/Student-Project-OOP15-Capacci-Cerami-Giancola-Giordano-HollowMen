package hollowmen.view.juls;

import java.util.Collection;

import hollowmen.enumerators.InputMenu;

/**
 * The {@code ComplexMenuImpl} class represents the concrete
 * implementation of {@link ComplexMenu}. It allows to draw "complex" menus on screen.
 * @author Juls
 */
public class ComplexMenuImpl implements ComplexMenu{

	public ComplexMenuImpl() {
	}
	
	@Override
	public void drawComplexMenu(InputMenu name, Collection<?> collection) {
		switch (name) {
		
		case INVENTORY:
		//	new Inventory(collection);
			break;
			
		case SKILL_TREE:
		//	new SkillTree(collection);
			break;
			
		case SHOP:
		//	new Shop(collection);
			break;
			
		case POKEDEX:
		//	new Pokedex(collection);
			break;
			
		case ACHIEVEMENTS:
		//	new Achievements(collection);
			break;
			
		default:
			break;
		
		}
		
	}

}
