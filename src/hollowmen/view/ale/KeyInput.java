package hollowmen.view.ale;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The {@code KeyInput} class is used to inform when a key is pressed
 * or released.
 * 
 * @author Alessia
 *
 */
public class KeyInput extends KeyAdapter{
	Game game;
	
	public KeyInput(Game game){
		this.game=game;
	}
	
	public void keyPressed(KeyEvent e){
		game.keyManager(e);
	}
	
	public void keyReleased(KeyEvent e){
		game.keyManager(e);
	}
}
