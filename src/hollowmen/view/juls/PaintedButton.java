package hollowmen.view.juls;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



/**
 * The {@link PaintedButton} Class creates customized buttons.
 * The background of the buttons is loaded from the "res" folder.
 * 
 * @author Juls
 * @version 3.0
 * @since 12/05
 * 
 * Last Update: 23/05 15:30
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
			buttonBG = ImageIO.read(new File("res/images/littleButton.png"));
			buttonOver = ImageIO.read(new File("res/images/littleButtonPressed.png"));
			buttonNA = ImageIO.read(new File("res/images/littleButtonNOTAvailable.png"));
			
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
