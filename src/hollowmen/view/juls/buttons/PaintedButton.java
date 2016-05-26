package hollowmen.view.juls.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The {@link PaintedButton} Class creates customized buttons.
 * It represents the button's type most used for the menus in the app. 
 * 
 * @author Juls
 */
public class PaintedButton extends TranslucentButton {
	
	private static final long serialVersionUID = -4128202845780333356L;
	private BufferedImage buttonBG, buttonOver, buttonNA;
	
	public PaintedButton(String text) {
		super(text);
		super.setPreferences();
		super.addMouseListener(ma);
		this.setForeground(Color.WHITE);
	}
	
	public void loadImages() {
		try {
			buttonBG = ImageIO.read(new File("res/images/buttons/pButton.png"));
			buttonOver = ImageIO.read(new File("res/images/buttons/pButtonOver.png"));
			buttonNA = ImageIO.read(new File("res/images/buttons/pButtonNA.png"));
			
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		loadImages();
		g.drawImage(buttonBG, 0, 8, null);
		if(isOver) {
			g.drawImage(buttonOver, 0, 8, null);
		}
		if(!isAvailable) {
			g.drawImage(buttonNA, 0, 8, null);
		}
		super.paintComponent(g); // must ALWAYS be at the end of the paintComponent() method
	}
}
