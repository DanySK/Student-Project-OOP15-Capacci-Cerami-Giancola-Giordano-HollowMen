package hollowmen.view;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.ImageIcon;
import hollowmen.controller.ViewObserver;
import hollowmen.enumerators.InputMenu;
import hollowmen.model.Point2D;
import hollowmen.model.facade.InformationDealer;
import hollowmen.utilities.Pair;

import hollowmen.view.ale.Game;
import hollowmen.view.juls.BasicMenuImpl;
import hollowmen.view.juls.ComplexMenuImpl;

/**
 * The ViewImpl class, that implements {@link View}, is used to draw application on screen.
 * 
 * @author Alessia
 * 
 */

public class ViewImpl implements View {
	
	private Game game;
	private Map<String,ImageIcon> storage;
	
	public ViewImpl(int x, int y, ViewObserver observer){
		SingletonFrame.setWidth(x);
		SingletonFrame.setHeight(y);
		game=new Game(x,y,observer);
	}
	
	/**
	 * The {@code drawMenu} method draws the menu on screen when needed.
	 * @param type - distinguishes the two kinds of menu (Basic or Complex)
	 * @param name - represents the menu to draw
	 * @param collection - (Optional) represents the pool of Items/Mobs/Skill Nodes/Achievements
	 * 						to add to the menu
	 * 
	 * @author Juls
	 * NOTE FOR ME: change this stupid "?" (Damn Object, you pineapple head)
	 */
	public void drawMenu(InputMenu type, InputMenu name, Optional<Collection<InformationDealer>> collection) {
		BasicMenuImpl basic = new BasicMenuImpl();
		ComplexMenuImpl complex = new ComplexMenuImpl();
		if (type.getType().equals("basic")) {
			basic.drawBasicMenu(name);
		} else {
			complex.drawComplexMenu(name, collection.get());
		}
	}

	/**
	 * The method {@code getFile} is used to take from the controller a list
	 * of all the files that the view needs.
	 */
	public void takeFile(Map<String, byte[]> fileMap){
		this.storage=new HashMap<String,ImageIcon>();
		for(Map.Entry<String,byte[]> elem: fileMap.entrySet()){
			this.storage.put(elem.getKey(),new ImageIcon(elem.getValue()));
		}
		this.game.setStorage(this.storage);
	}
	
	/**
	 * The method {@code drawGame} is used to draw all the components on screen.
	 * It's linked to {@link Game} class.
	 */ 
	public void drawGame(List<Pair<String, Point2D>> componentList) {
		game.draw(componentList);
	}
}
