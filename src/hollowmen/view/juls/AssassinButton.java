package hollowmen.view.juls;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AssassinButton extends TranslucentButton {

	private static final long serialVersionUID = -2065236498744095705L;
	private BufferedImage assassin, assassinOver, assassinSelected, assassinNA;
	
	public AssassinButton() {
		super.setPreferences();
		super.addMouseListener(ma);
	}
	
	public void loadImages() {
		try {
			assassin = ImageIO.read(new File(""));
			assassinSelected = ImageIO.read(new File(""));
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
		if(isClicked) {
			g.drawImage(assassinSelected, 0, 0, null);
		}
		if(!isAvailable) {
			g.drawImage(assassinNA, 0, 0, null);
		}
		super.paintComponent(g); // must ALWAYS be at the end of the paintComponent() method
	}

}
