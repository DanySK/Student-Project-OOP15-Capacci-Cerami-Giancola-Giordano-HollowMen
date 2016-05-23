package hollowmen.view.juls;

import java.util.Collection;

public class ComplexMenuImpl implements ComplexMenu{

	public ComplexMenuImpl() {
	}
	
	@Override
	public void drawComplexMenu(MenuType name, Collection<?> collection) {
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
