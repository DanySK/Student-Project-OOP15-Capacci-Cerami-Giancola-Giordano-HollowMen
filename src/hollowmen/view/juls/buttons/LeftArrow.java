package hollowmen.view.juls.buttons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The {@code LeftArrow} class it's one of the specialization of the
 * {@link TranslucentButton} abstract class. It represents an arrow facing left.
 * 
 * @author Juls
 *
 */
public class LeftArrow extends TranslucentButton {

	private static final long serialVersionUID = 583129785119111280L;
	private BufferedImage lArrow, lArrowOver, lArrowNA;
	
	public LeftArrow() {
		super.setPreferences();
		super.addMouseListener(ma);
	}
	
	public void loadImages() {
		try {
			lArrow = ImageIO.read(new File("res/images/buttons/LArrow.png"));
			lArrowOver = ImageIO.read(new File("res/images/buttons/LArrowOver.png"));
			lArrowNA = ImageIO.read(new File("res/images/buttons/LArrowNA.png"));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		loadImages();
		g.drawImage(lArrow, 0, 0, null);
		if(isOver) {
			g.drawImage(lArrowOver, 0, 0, null);
		}
		if(!isAvailable) {
			g.drawImage(lArrowNA, 0, 8, null);
		}
		super.paintComponent(g);
	}

}
