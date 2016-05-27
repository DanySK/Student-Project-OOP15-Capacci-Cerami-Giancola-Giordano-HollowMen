package hollowmen.view;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import hollowmen.controller.ViewObserver;
import hollowmen.enumerators.InputMenu;
import hollowmen.model.Point2D;
import hollowmen.utilities.Pair;

/**
 * The View interface allows to draw application on screen.
 * 
 * @author Alessia
 *
 */
public interface View {
	
	/**
	 * The method {@code getFile} is used to take from the controller a list
	 * of all the files that the view needs.
	 *
	 * @param fileList
	 */
	public void takeFile(Map<String, byte[]> fileList);
	
	/**
	 * The {@code drawMenu} method draws the menu on screen when needed.
	 * @param text - distinguishes the two kinds of menu (Basic or Complex)
	 * @param name - represents the menu to draw
	 * @param collection - (Optional) represents the pool of Items/Mobs/Skill Nodes/Achievements
	 * 						to add to the menu
	 * 
	 * @author Juls
	 */
	public void drawMenu(String text, InputMenu name, Optional<Collection<?>> collection);
	
	/**
	 * The method {@code drawGame} is used to take from the controller a list
	 * of all the components that the view needs to draw.
	 * 
	 * @param componentList 
	 */
	public void drawGame(List<Pair<String,Point2D>> componentList);
	
}
