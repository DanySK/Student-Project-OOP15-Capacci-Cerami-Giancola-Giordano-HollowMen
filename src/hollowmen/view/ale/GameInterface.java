package hollowmen.view.ale;

import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ImageIcon;

import hollowmen.controller.ViewObserver;
import hollowmen.model.Point2D;
import hollowmen.utilities.Pair;

/**
 * The GameInterface interface makes some methods available in order to 
 * set a ViewObserver,to draw all the elements on screen and to read the
 * gamer's inputs.
 * 
 * @author Alessia
 *
 */
public interface GameInterface {
	
	/**
	 * The method {@code setObserver} is used to set a {@link ViewObserver}.
	 * @param observer
	 */
	public void setObserver(ViewObserver observer);
	
	/**
	 * The method {@code draw} is used to draw all the components on screen.
	 * 
	 * @param componentList
	 */
	public void draw(List<Pair<String, Point2D>> componentList);
	
	/**
	 * The method{@code setStorage} id used to create a list of JLabel with the associated {@link ImageIcon}.
	 * 
	 * @param storage
	 */
	public void setStorage(List<Pair<String,ImageIcon>> storage);
	
	/**
	 * The method {@code keyPressed} is used to read all the gamer's inputs.
	 */
	public void keyPressed(KeyEvent e);
}
