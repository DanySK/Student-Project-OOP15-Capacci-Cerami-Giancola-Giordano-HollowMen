package hollowmen.view;

import java.util.LinkedList;

import java.util.List;
import javax.swing.ImageIcon;
import hollowmen.controller.ViewObserver;
import hollowmen.model.Point2D;
import hollowmen.utilities.Pair;
import hollowmen.view.ale.Game;

/**
 * The ViewImpl class, that implements {@link View}, is used to draw application on screen.
 * 
 * @author Alessia
 * 
 */

public class ViewImpl implements View {
	
	private ViewObserver observer;
	private Game game;
	
	public ViewImpl(int x, int y){
		SingletonFrame.setWidth(x);
		SingletonFrame.setHeight(y);
		game=new Game(x,y);
	}
	
	/**
	 * 
	 */
	public void drawMenu() {
		

	}

	/**
	 * The method {@code getFile} is used to take from the controller a list
	 * of all the files that the view needs.
	 */
	public void getFile(List<Pair<String, byte[]>> fileList) {
		List<Pair<String,ImageIcon>> storage=new LinkedList<Pair<String,ImageIcon>>();
		for(Pair<String,byte[]> elem: fileList){
			storage.add(new Pair<String,ImageIcon>(elem.getX(),new ImageIcon(elem.getY())));
		}
	}
	/**
	 * The method {@code drawGame} is used to draw all the components on screen.
	 */ 
	public void drawGame(List<Pair<String, Point2D>> componentList) {
		game.draw(componentList);
	}

	/**
	 * The method {@code setObserver} to set a ViewObserver used 
	 * to process inputs.
	 */
	public void setObserver(ViewObserver observer) {
		this.observer=observer;
		this.game.setObserver(observer);
		
	}

}
