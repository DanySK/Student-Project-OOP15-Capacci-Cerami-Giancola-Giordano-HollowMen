package hollowmen.view;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import hollowmen.controller.ViewObserver;
import hollowmen.enumerators.InputMenu;
import hollowmen.model.facade.InformationDealer;
import hollowmen.model.facade.Point2D;

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
	public void takeFile(Map<String, byte[]> fileMap);
	
	/**
	 * The {@code drawMenu} method draws the menu on screen when needed.
	 * @param type - distinguishes the two kinds of menu (Basic or Complex)
	 * @param name - represents the menu to draw
	 * @param collection - (Optional) represents the pool of Items/Mobs/Skill Nodes/Achievements
	 * 						to add to the menu
	 * 
	 * @author Juls
	 */
	public void drawMenu(InputMenu type, InputMenu name, Optional<Collection<InformationDealer>> collection);
	
	/**
	 * The method {@code drawGame} is used to take from the controller a list
	 * of all the components that the view needs to draw.
	 * 
	 * @param componentList 
	 */
	public void drawGame(Map<String,Point2D> componentMap);
	
}
