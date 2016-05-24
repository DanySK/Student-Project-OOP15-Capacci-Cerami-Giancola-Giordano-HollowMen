package hollowmen.view.juls.buttons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The {@code MageButton} class it's one of the concrete classes 
 * that extend {@link TranslucentButton} abstract class. It is used in the ClassChoiceMenu.
 * @author Juls
 *
 */
public class MageButton extends TranslucentButton {

	private static final long serialVersionUID = -2065213613244095705L;
	private BufferedImage mage, mageOver, mageSelected, mageNA;
	
	public MageButton() {
		super.setPreferences();
		super.addMouseListener(ma);
	}
	
	public void loadImages() {
		try {
			mage = ImageIO.read(new File(""));
			mageSelected = ImageIO.read(new File(""));
			mageNA = ImageIO.read(new File(""));
			mageOver = ImageIO.read(new File(""));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		loadImages();
		g.drawImage(mage, 0, 0, null);
		if(isOver) {
			g.drawImage(mageOver, 0, 0, null);
		}
		if(isClicked) {
			g.drawImage(mageSelected, 0, 0, null);
		}
		if(!isAvailable) {
			g.drawImage(mageNA, 0, 0, null);
		}
		super.paintComponent(g); // must ALWAYS be at the end of the paintComponent() method
	}

}
