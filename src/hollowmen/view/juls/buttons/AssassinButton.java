package hollowmen.view.juls.buttons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The {@code AssassinButton} class it's one of the concrete classes 
 * that extends {@link TranslucentButton}. It consists into a custom button that is used
 * in the ClassChoiceMenu.
 * 
 * @author Juls
 *
 */
public class AssassinButton extends TranslucentButton {

	private static final long serialVersionUID = -2065236498744095705L;
	private BufferedImage assassin, assassinOver, assassinNA;
	
	public AssassinButton() {
		super.setPreferences();
		super.addMouseListener(ma);
	}
	
	public void loadImages() {
		try {
			assassin = ImageIO.read(new File(""));
			assassinNA = ImageIO.read(new File(""));
			assassinOver = ImageIO.read(new File(""));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		loadImages();
		g.drawImage(assassin, 0, 0, null);
		if(isOver) {
			g.drawImage(assassinOver, 0, 0, null);
		}
		if(!isAvailable) {
			g.drawImage(assassinNA, 0, 0, null);
		}
		super.paintComponent(g);
	}

}
