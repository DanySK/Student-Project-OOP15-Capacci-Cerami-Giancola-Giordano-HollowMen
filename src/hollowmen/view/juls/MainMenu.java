package hollowmen.view.juls;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hollowmen.controller.ViewObserver;
import hollowmen.view.SingletonFrame;

/**
 * A really SIMPLE and BASIC version of the MainMenu.
 * This will be improved and cleaned.
 * (Need to be "smarter"...)
 * 
 * @author Juls
 * @version 2.0
 * @since 11/05
 * 
 * Last Update: 19/05 14:40
 */
public class MainMenu extends JFrame {

	private static final long serialVersionUID = 3001623152687149057L;
	private static final int TITLE_X = 30;
	private static final int TITLE_Y = 50;
	private static final int TITLE_WIDTH = 270;
	private static final int TITLE_HEIGHT = 190;
	
	private JPanel buttonsContainer = new JPanel(); // will contain the buttons (no surprise)

	private PaintedButton newGame = new PaintedButton("NEW GAME");
	private PaintedButton loadGame = new PaintedButton("LOAD GAME");
	private PaintedButton help = new PaintedButton("HELP");
	private PaintedButton credits = new PaintedButton("CREDITS");
	private PaintedButton exit = new PaintedButton("EXIT");

	private final JLabel label = new JLabel();
	private final JLabel title = new JLabel();
	
	private ViewObserver observer; //needed to tell Controller what happens
	

	protected MainMenu() {
		
		// obtaining a reference to the only instance of the SingletonFrame class
		SingletonFrame frame = SingletonFrame.getInstance();
		
		// loading of the images (background and title)
		try {
			label.setIcon(new ImageIcon(ImageIO.read(new File("res/images/castle.jpg"))));
			title.setIcon(new ImageIcon(ImageIO.read(new File("res/images/title.png"))));			
		} catch (IOException e) {
			e.printStackTrace();
		}
		// setting "label" as background image
		frame.setContentPane(label);
		
		title.setBounds(TITLE_X, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);
		
		buttonsContainer.setOpaque(false);	// lets see the background under the component
		buttonsContainer.setLayout(new GridLayout(5,1,8,0)); // GridLayout it's great for a menu
		buttonsContainer.add(newGame);
		buttonsContainer.add(loadGame);
		buttonsContainer.add(help);
		buttonsContainer.add(credits);
		buttonsContainer.add(exit);
		buttonsContainer.setBounds(90, 240, 150, 300);
		
		newGame.setEnabled(false);
		
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomDialog(frame, "<html>You are going to start a New Adventure. <br> Are you ready?<html>");
			}
		});
		
		loadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		credits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		frame.add(title);
		frame.add(buttonsContainer);
		frame.setVisible(true);
		
	}

	/* This main is here just for me, will be deleted once everything works... */
	public static void main(String args[]) {
		SingletonFrame.setWidth(800);
		SingletonFrame.setHeight(600);
		new MainMenu();
	}

}
