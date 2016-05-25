package hollowmen.view.juls.buttons;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The {@code NodeButton} class allows to graphically represent the nodes
 * of the SkillTree. Every node is a button.
 * 
 * TODO : finish the implementation
 * @author Juls
 *
 */
public class NodeButton extends TranslucentButton {
	
	private static final long serialVersionUID = 7681825185715821047L;
	private BufferedImage node, nodeOver, nodeSelected, nodeNA;

	
	public NodeButton() {
		super.addMouseListener(ma);
	}
	
	public void loadImages() {
		try {
			node = ImageIO.read(new File(""));
			nodeOver = ImageIO.read(new File(""));
			nodeSelected = ImageIO.read(new File(""));
			nodeNA = ImageIO.read(new File(""));
		} catch (IOException e){
			e.printStackTrace();
		}
	}

}
