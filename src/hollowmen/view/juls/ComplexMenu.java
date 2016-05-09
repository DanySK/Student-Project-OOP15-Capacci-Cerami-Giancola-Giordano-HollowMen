package hollowmen.view.juls;

import java.util.Collection;

/**
 * The interface {@code ComplexMenu} allows to draw more complex menus,
 * such as Inventory, Shop or Skill Tree.
 * @author Juls
 *
 * @param <X> a collection of unmodifiable Objects, such as
 * Items or Mobs.
 */
public interface ComplexMenu<X> {
	
	/**
	 * The method draws a complex menu taking as argument
	 * a Collection of unmodifiable Generics.
	 * 
	 * @param collection - the collection that must be added
	 * to the Menu and graphically represented on screen.
	 */
	public void drawComplexMenu(Collection<X> collection);

}
