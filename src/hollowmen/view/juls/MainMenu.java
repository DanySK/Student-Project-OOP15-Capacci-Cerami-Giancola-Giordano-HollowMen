package hollowmen.view.juls;

import java.awt.GridLayout;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A really SIMPLE and BASIC version of the MainMenu.
 * This will be improved and cleaned.
 * (Need to be "smarter"...)
 * 
 * @author Juls
 * @version 1.2
 * @since 11/05
 * 
 * Last Update: 12/05 19:25
 */
public class MainMenu extends JFrame {

	private static final long serialVersionUID = 3001623152687149057L;
	
	private JFrame frame = new JFrame("HOLLOW MEN");
	private JPanel buttonsContainer = new JPanel(); // will contain the buttons (no surprise)

	private PaintedButton newGame = new PaintedButton("NEW GAME");
	private PaintedButton loadGame = new PaintedButton("LOAD GAME");
	private PaintedButton help = new PaintedButton("HELP");
	private PaintedButton credits = new PaintedButton("CREDITS");
	private PaintedButton exit = new PaintedButton("EXIT");

	private final JLabel label = new JLabel();
	private final JLabel title = new JLabel();
	
	/* NOTE: these two (for now lazily init) fields are needed to call frame.setSize() */
	private int width = 800;
	private int height = 600;
		
	private MainMenu() {
		
		frame.setSize(width, height); // will be soon arranged with the dimension decided by the Model
		frame.setResizable(false); // because I don't want to mess up with the dimension

		// loading of the images (background and title)
		try {
			label.setIcon(new ImageIcon(ImageIO.read(new File("res/castle.jpg"))));
			title.setIcon(new ImageIcon(ImageIO.read(new File("res/title.png"))));			
		} catch (IOException e) {
			e.printStackTrace();
		}
		// setting "label" as background image
		frame.setContentPane(label);
	
		title.setBounds(30, 50, 270, 190);
		
		buttonsContainer.setOpaque(false);	// lets see the background under the component
		buttonsContainer.setLayout(new GridLayout(5,1,8,0)); // GridLayout it's great for a menu
		buttonsContainer.add(newGame);
		buttonsContainer.add(loadGame);
		buttonsContainer.add(help);
		buttonsContainer.add(credits);
		buttonsContainer.add(exit);
		buttonsContainer.setBounds(90, 240, 150, 300);
		
		frame.add(title);
		frame.add(buttonsContainer);
		frame.setVisible(true);
		
	}

	/* This main is here just for me, will be deleted once everything works... */
	public static void main(String args[]) {
		new MainMenu();
	}

}
