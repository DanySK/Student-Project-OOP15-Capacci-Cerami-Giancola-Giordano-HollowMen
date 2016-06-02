package hollowmen.view.juls.dialog;

import java.awt.Color;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import hollowmen.view.SingletonFrame;
/**
 * The {@code MenuDialog} abstract class defines the preferences of 
 * some menus, such as Inventory, Skill Tree, Shop, Bestiary and Achievements.
 * @author Juls
 *
 */
public abstract class MenuDialog extends CustomDialog {

	private static final long serialVersionUID = 1935614271807195916L;
	protected JLabel title = new JLabel();

	public MenuDialog(Frame frame) {
		super(frame);
		this.setPreferences();
		this.addBackground();
	}
	
	public void setPreferences() {
		this.setSize(750, 550);
		this.setUndecorated(true);
		this.setLocationRelativeTo(SingletonFrame.getInstance());
		this.setModal(false);
		this.addBackground();
		this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	@Override
	protected void addBackground() {
		try {
			background.setIcon(new ImageIcon(ImageIO.read(new File("res/images/backgrounds/pergamena.jpg"))));
			this.setContentPane(background);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * The {@code addTitle} method adds title image to the menu.
	 */
	protected void addTitle(JLabel title) {
		title.setBounds(50, 30, 210, 60);
		this.add(title);
	}

}
