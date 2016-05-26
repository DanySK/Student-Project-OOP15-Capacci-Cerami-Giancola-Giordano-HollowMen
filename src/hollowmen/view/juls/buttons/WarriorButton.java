package hollowmen.view.juls.buttons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The {@code WarrioButton} class it's one of the concrete classes 
 * that extend {@link TranslucentButton}. The first time the user
 * runs the app, the Warrior Class (and so the relative button) will be
 * the only one available in the ClassChoiceMenu.
 * @author Juls
 *
 */
public class WarriorButton extends TranslucentButton {

	private static final long serialVersionUID = 796229786983111280L;
	private BufferedImage warrior, warriorOver;
	
	public WarriorButton() {
		super.setPreferences();
		super.addMouseListener(ma);
	}
	
	public void loadImages() {
		try {
			warrior = ImageIO.read(new File(""));
			warriorOver = ImageIO.read(new File(""));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		loadImages();
		g.drawImage(warrior, 0, 0, null);
		if(isOver) {
			g.drawImage(warriorOver, 0, 0, null);
		}
		super.paintComponent(g); // must ALWAYS be at the end of the paintComponent() method
	}

}