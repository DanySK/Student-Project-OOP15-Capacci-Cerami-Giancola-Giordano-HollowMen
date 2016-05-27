package hollowmen.view.juls.buttons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The {@code NodeButton} class allows to graphically represent the nodes
 * of the SkillTree. Every node is a button.
 * 
 * @author Juls
 */
public class NodeButton extends TranslucentButton {
	
	private static final long serialVersionUID = 7681825185715821047L;
	private BufferedImage nodeOver, nodeUnlocked, nodeNA, nodeAvailable;

	
	public NodeButton() {
		super.addMouseListener(ma);
		this.loadImages();
	}
	
	public void loadImages() {
		try {
			nodeOver = ImageIO.read(new File("res/images/buttons/nodeOver.png"));
			nodeUnlocked = ImageIO.read(new File("res/images/buttons/nodeUnlocked.png"));
			nodeNA = ImageIO.read(new File("res/images/buttons/nodeNA.png"));
			nodeAvailable = ImageIO.read(new File("res/images/buttons/nodeAvailable.png"));
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		loadImages();
		g.drawImage(nodeAvailable, 0, 8, null); // nodo disponibile
		if(isOver && isAvailable) { // mouse sopra, è disponibile, non è ancora sbloccato
			g.drawImage(nodeOver, 0, 8, null);
		} else if(isUnlocked) { // nodo sbloccato
			g.drawImage(nodeUnlocked, 0, 8, null);
		} else if (!isAvailable) { // nodo non disponibile
			g.drawImage(nodeNA, 0, 8, null);
		}
		super.paintComponent(g);
	}
}
