package hollowmen.view.juls;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WarriorButton extends TranslucentButton {

	private static final long serialVersionUID = 796229786983111280L;
	private BufferedImage warrior, warriorOver, warriorSelected;
	
	public WarriorButton() {
		super.setPreferences();
		super.addMouseListener(ma);
	}
	
	public void loadImages() {
		try {
			warrior = ImageIO.read(new File(""));
			warriorOver = ImageIO.read(new File(""));
			warriorSelected = ImageIO.read(new File(""));
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
		if(isClicked) {
			g.drawImage(warriorSelected, 0, 0, null);
		}
		super.paintComponent(g); // must ALWAYS be at the end of the paintComponent() method
	}

}