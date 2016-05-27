package hollowmen.view.juls;

import java.util.Collection;

import hollowmen.enumerators.InputMenu;

/**
 * The interface {@code ComplexMenu} allows to draw more complex menus,
 * such as Inventory, Shop or Skill Tree.
 * @author Juls
 *
 * @param <X> - a collection of unmodifiable Objects, such as
 * Items or Mobs.
 */
public interface ComplexMenu {
	
	/**
	 * The method draws a Complex Menu.
	 * 
	 * @param name - the name of the menu that must be drawn on screen
	 * @param collection - the collection that must be added
	 * to the Menu and graphically represented on screen.
	 */
	public void drawComplexMenu(InputMenu name, Collection<?> collection);

}
