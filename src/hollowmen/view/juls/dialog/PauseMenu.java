package hollowmen.view.juls.dialog;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hollowmen.view.juls.MainMenu;
import hollowmen.view.juls.buttons.PaintedButton;

/**
 * The {@code PauseMenu} class pauses the game. It allows to go back to
 * the Lobby or to the MainMenu.
 * @author Juls
 *
 */
public class PauseMenu extends MessageDialog {

	private static final long serialVersionUID = -4193668569929345701L;
	private JLabel title = new JLabel();
	private PaintedButton lobby = new PaintedButton("TO LOBBY");
	private PaintedButton main = new PaintedButton("TO MAIN");
	private PaintedButton resume = new PaintedButton("RESUME");
	private JPanel buttonContainer = new JPanel();
	private JPanel titleContainer = new JPanel();

	public PauseMenu(Frame frame) {
		super(frame);
		loadImage();

		titleContainer.setLayout(new GridLayout(1, 0, 0, 0));
		titleContainer.setBounds(115, 30, 270, 70);
		titleContainer.setOpaque(false);
		titleContainer.add(title);
		add(titleContainer);
		
		buttonContainer.setLayout(new GridLayout(1, 3, 10, 0));
		buttonContainer.setBounds(15, 110, 470, 58);
		buttonContainer.setOpaque(false);
		buttonContainer.add(lobby);
		buttonContainer.add(main);
		buttonContainer.add(resume);
		add(buttonContainer);
		
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainMenu();
			}
		});
		
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		setVisible(true);
	}
	
	private void loadImage() {
		try {
			title.setIcon(new ImageIcon(ImageIO.read(new File("res/images/titles/pauseMenu.png"))));			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
