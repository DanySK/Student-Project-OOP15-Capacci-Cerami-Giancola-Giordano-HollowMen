package hollowmen.view;

import java.util.List;
import hollowmen.controller.ViewObserver;
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
	public void getFile(List<Pair<String, byte[]>> fileList);
	
	/**
	 * TODO
	 */
	public void drawMenu(/*da aggiungere*/);
	
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
