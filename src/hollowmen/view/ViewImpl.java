package hollowmen.view;

import java.util.Collection;
import java.util.LinkedList;

import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import hollowmen.controller.ViewObserver;
import hollowmen.model.Point2D;
import hollowmen.utilities.Pair;

import hollowmen.view.ale.Game;
import hollowmen.view.juls.BasicMenuImpl;
import hollowmen.view.juls.ComplexMenuImpl;
import hollowmen.view.juls.MenuType;

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
	 * The {@code drawMenu} method draws the menu on screen when needed.
	 * @param text - distinguishes the two kinds of menu (Basic or Complex)
	 * @param name - represents the menu to draw
	 * @param collection - (Optional) represents the pool of Items/Mobs/Skill Nodes/Achievements
	 * 						to add to the menu
	 * 
	 * @author Juls
	 * NOTE FOR ME: change this stupid "?" (Damn Object, you pineapple head)
	 */
	public void drawMenu(String text, MenuType name, Optional<Collection<?>> collection) {
		BasicMenuImpl basic = new BasicMenuImpl();
		ComplexMenuImpl complex = new ComplexMenuImpl();
		if (text.equalsIgnoreCase("basic")) {
			basic.drawBasicMenu(name);
		} else {
			complex.drawComplexMenu(name, collection.get());
		}
	}

	/**
	 * The method {@code getFile} is used to take from the controller a list
	 * of all the files that the view needs.
	 */
	public void takeFile(List<Pair<String, byte[]>> fileList) {
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
