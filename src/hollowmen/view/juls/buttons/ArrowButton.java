package hollowmen.view.juls.buttons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ArrowButton extends TranslucentButton {

	private static final long serialVersionUID = 2261116517867429353L;
	private BufferedImage rArrow, rArrowOver, rArrowNA, lArrow, lArrowOver, lArrowNA;
	private Direction direction;
	
	public ArrowButton(Direction d) {
		super.setPreferences();
		this.setDirection(d);
		this.loadImages();
	}
	
	@Override
	protected void loadImages() {
		try {
			rArrow = ImageIO.read(new File("res/images/buttons/RArrow.png"));
			rArrowOver = ImageIO.read(new File("res/images/buttons/RArrowOver.png"));
			rArrowNA = ImageIO.read(new File("res/images/buttons/RArrowNA.png"));
			lArrow = ImageIO.read(new File("res/images/buttons/LArrow.png"));
			lArrowOver = ImageIO.read(new File("res/images/buttons/LArrowOver.png"));
			lArrowNA = ImageIO.read(new File("res/images/buttons/LArrowNA.png"));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	protected void setDirection(Direction d) {
		this.direction = d;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if (direction.equals(Direction.LEFT)) {
			g.drawImage(lArrow, 0, 0, null);
			if(isOver) {
			g.drawImage(lArrowOver, 0, 0, null);
			}
			if(!isAvailable) {
				g.drawImage(lArrowNA, 0, 8, null);
			}
		} else {
			g.drawImage(rArrow, 0, 8, null);
			if(isOver) {
				g.drawImage(rArrowOver, 0, 8, null);
			}
			if(!isAvailable) {
				g.drawImage(rArrowNA, 0, 8, null);
			}
		}
		super.paintComponent(g);
	}
}
