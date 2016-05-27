package hollowmen.view.juls.buttons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The {@code RightArrow} class it's a specialization of
 * {@link TranslucentButton}. It represents an arrow facing right.
 * @author Juls
 *
 */
public class RightArrow extends TranslucentButton {

	private static final long serialVersionUID = 722229785119111280L;
	private BufferedImage rArrow, rArrowOver, rArrowNA;
	
	public RightArrow() {
		super.setPreferences();
	}
	
	public void loadImages() {
		try {
			rArrow = ImageIO.read(new File("res/images/buttons/RArrow.png"));
			rArrowOver = ImageIO.read(new File("res/images/buttons/RArrowOver.png"));
			rArrowNA = ImageIO.read(new File("res/images/buttons/RArrowNA.png"));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		loadImages();
		g.drawImage(rArrow, 0, 8, null);
		if(isOver) {
			g.drawImage(rArrowOver, 0, 8, null);
		}
		if(!isAvailable) {
			g.drawImage(rArrowNA, 0, 8, null);
		}
		super.paintComponent(g); // must ALWAYS be at the end of the paintComponent() method
	}
}
