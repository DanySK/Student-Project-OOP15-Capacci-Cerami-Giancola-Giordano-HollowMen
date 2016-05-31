package hollowmen.view;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.swing.ImageIcon;
import hollowmen.controller.ViewObserver;
import hollowmen.enumerators.InputMenu;
import hollowmen.model.facade.DrawableRoomEntity;
import hollowmen.model.facade.InformationDealer;
import hollowmen.view.ale.Game;
import hollowmen.view.ale.Lobby;
import hollowmen.view.juls.BasicMenuImpl;
import hollowmen.view.juls.ComplexMenuImpl;

/**
 * The ViewImpl class, that implements {@link View}, is used to draw the application on screen.
 * 
 * @author Alessia
 * 
 */

public class ViewImpl implements View {
	
	private Game game;
	private Map<String,ImageIcon> storage;
	private ViewObserver observer;
	
	public ViewImpl(int x, int y, ViewObserver observer){
	    this.observer=observer;
		SingletonFrame.setWidth(x);
		SingletonFrame.setHeight(y);
		game=new Game(x,y,observer);
		SingletonFrame.getInstance().add(game);
		
	}
	
	/**
	 * The {@code drawMenu} method draws the menu on screen when needed.
	 * @param type - distinguishes the two kinds of menu (Basic or Complex)
	 * @param name - represents the menu to draw
	 * @param collection - (Optional) represents the pool of InformationDealer (Items/Mobs/Skill Nodes/Achievements)
	 * 						to add to the menu
	 * 
	 * @author Juls
	 */
	public void drawMenu(InputMenu name, Optional<Collection<InformationDealer>> collection) {
		BasicMenuImpl basic = new BasicMenuImpl();
		ComplexMenuImpl complex = new ComplexMenuImpl();
		if (name.getType().equals("basic")) {
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
	public void drawGame(List<DrawableRoomEntity> componentList) {
		game.draw(componentList);
	}
	
	public void drawLobby(){
	    SingletonFrame.getInstance().add(new Lobby(this.observer, this.storage));
	}
	/**
	 * The {@code getStorage()} method allows to get the images' storage.
	 * @return - storage
	 */
	public Map<String,ImageIcon> getStorage() {
		return storage;
	}
}
