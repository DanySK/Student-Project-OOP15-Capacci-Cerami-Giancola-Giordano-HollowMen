package hollowmen.view.juls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import hollowmen.controller.ViewObserver;
import hollowmen.view.SingletonFrame;
import hollowmen.view.juls.buttons.PaintedButton;
import hollowmen.view.juls.dialog.CreditsMenu;
import hollowmen.view.juls.dialog.ExitDialog;
import hollowmen.view.juls.dialog.HelpMenu;
import hollowmen.view.juls.dialog.NewGameDialog;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code MainMenu} class it's a visual representation
 * of the Main Menu of the app. From here, the user can start using
 * the software.
 * 
 * @author Juls
 */
public class MainMenu extends JFrame {

	private static final long serialVersionUID = 3001623152687149057L;

	private PaintedButton newGame = new PaintedButton("NEW GAME");
	private PaintedButton loadGame = new PaintedButton("LOAD GAME");
	private PaintedButton help = new PaintedButton("HELP");
	private PaintedButton credits = new PaintedButton("CREDITS");
	private PaintedButton exit = new PaintedButton("EXIT");
	private String name;

	private JPanel buttonsContainer = PanelBuilder.getBuilder()
									.layout(5,1,8,0)
									.bound(150, 300, 150, 300)
									.addTo(newGame)
									.addTo(loadGame)
									.addTo(help)
									.addTo(credits)
									.addTo(exit)
									.build();
	
	private final JLabel label = new JLabel();
	private final JLabel title = new JLabel();
	
	//private ViewObserver observer; 
	
	public MainMenu() {
		SingletonFrame frame = SingletonFrame.getInstance();
		this.loadImages();
		title.setBounds(100, 70, 270, 190);
		this.addToList();
		frame.setContentPane(label);
		frame.add(title);
		frame.add(buttonsContainer);
		frame.setVisible(true);
	}
	
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			name = ((PaintedButton) e.getSource()).getText();
			if(name.equals("NEW GAME")) {
				new NewGameDialog(SingletonFrame.getInstance());
			} else if (name.equals("LOAD GAME")) {
//				observer.addInput(InputCommand.LOAD);
			} else if (name.equals("HELP")) {
				new HelpMenu(SingletonFrame.getInstance());
			} else if (name.equals("CREDITS")) {
				new CreditsMenu(SingletonFrame.getInstance());
			} else {
				new ExitDialog(SingletonFrame.getInstance());
			}
		}
	};
	
	/**
	 * This method add a listener to the buttons
	 */
	private void addToList() {
		List<PaintedButton> buttonList = new ArrayList<>();
		
		buttonList.add(newGame);
		buttonList.add(loadGame);
		buttonList.add(help);
		buttonList.add(credits);
		buttonList.add(exit);
		
		loadGame.setEnabled(false);
		
		for(PaintedButton b : buttonList) {
			b.addActionListener(listener);
		}
	}
	
	/**
	 * The method tries to load the images needed.
	 */
	private void loadImages() {
		try {
			label.setIcon(new ImageIcon(ImageIO.read(new File("res/images/backgrounds/castleBG.jpg"))));
			title.setIcon(new ImageIcon(ImageIO.read(new File("res/images/titles/title.png"))));			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* This main is here just for me, will be deleted once everything works... */
	public static void main(String args[]) {
		SingletonFrame.setWidth(800);
		SingletonFrame.setHeight(600);
		new MainMenu();
	}

}
