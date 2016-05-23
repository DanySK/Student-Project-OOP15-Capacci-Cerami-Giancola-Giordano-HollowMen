package hollowmen.view;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import hollowmen.controller.ViewObserver;
import hollowmen.model.Point2D;
import hollowmen.utilities.Pair;
import hollowmen.view.juls.MenuType;

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
	public void takeFile(List<Pair<String, byte[]>> fileList);
	
	/**
	 * The {@code drawMenu} method draws the menu on screen when needed.
	 * @param text - distinguishes the two kinds of menu (Basic or Complex)
	 * @param name - represents the menu to draw
	 * @param collection - (Optional) represents the pool of Items/Mobs/Skill Nodes/Achievements
	 * 						to add to the menu
	 * 
	 * @author Juls
	 */
	public void drawMenu(String text, MenuType name, Optional<Collection<?>> collection);
	
	/**
	 * The method {@code drawGame} is used to take from the controller a list
	 * of all the components that the view needs to draw.
	 * 
	 * @param componentList 
	 */
	public void drawGame(List<Pair<String,Point2D>> componentList);
	
	/**
	 * The method {@code setObserver} to set a ViewObserver used 
	 * to process inputs.
	 */
	public void setObserver(ViewObserver observer);
	
}
