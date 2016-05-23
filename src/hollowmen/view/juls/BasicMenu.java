package hollowmen.view.juls;

/**
 * The interface {@code BasicMenu} allows to draw 
 * really simple game menus.
 * 
 * For example, Main Menu, Class Choice Menu or
 * Help Menu.
 * 
 * @author Juls
 * @since 09/05
 * 
 * Last Update: 18/05 20:40
 *
 */
public interface BasicMenu {
	/**
	 * This method is used to draw those Menus that does not need
	 * anything else.
	 * 
	 * @param name - the name of the menu that must be drawn
	 */
	public void drawBasicMenu(MenuType name);

}