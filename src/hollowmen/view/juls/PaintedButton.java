package hollowmen.view.juls;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * The {@link PaintedButton} Class creates customized buttons.
 * The background of the buttons is loaded from the "res" folder.
 * 
 * @author Juls
 * @version 1.2
 * @since 12/05
 * 
 * Last Update: 12/05 19:25
 */
public class PaintedButton extends JButton {
	
	private static final long serialVersionUID = -4128202845780339156L;
	private BufferedImage buttonBG;
	
	public PaintedButton(String text) {
		super(text);
		setPreferences();
	}
	
	/**
	 * The {@code setPreferences()} method determines the look&feel of 
	 * the {@link PaintedButton}'s buttons.
	 */
	private void setPreferences() {
		setContentAreaFilled(false);
        setOpaque(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
	}
	
	/**
	 * The {@code loadImages()} method loads the images that the constructor
	 * of the {@code PaintedButton} class will use as background for the buttons.
	 * <br>
	 * It catches IOException.
	 */
	private void loadImages() {
		try {
			buttonBG = ImageIO.read(new File("res/littleButton.png"));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		loadImages();
		g.drawImage(buttonBG, 0, 8, null);
		super.paintComponent(g); // must ALWAYS be at the end of the paintComponent() method
	}

}
