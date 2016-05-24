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

import hollowmen.view.juls.buttons.PaintedButton;

/**
 * The {@code CreditsMenu} class shows all information about 
 * the programmers of the app.
 * @author Juls
 *
 */
public class CreditsMenu extends OptionDialog {

	private static final long serialVersionUID = -7385165484666077280L;
	private JLabel credits = new JLabel();
	private JPanel panel = new JPanel();
	private JPanel buttonC = new JPanel();
	private PaintedButton close = new PaintedButton("CLOSE");

	public CreditsMenu(Frame frame) {
		super(frame);
		loadImages();
		
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		panel.setBounds(0, 0, 680, 500);
		panel.setOpaque(false);
		panel.add(credits);
		
		buttonC.setLayout(new GridLayout(1, 0, 0, 0));
		buttonC.setBounds(270, 415, 150, 58);
		buttonC.setOpaque(false);
		buttonC.add(close);
		
		add(panel);
		add(buttonC);
		
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		setVisible(true);
	}

	protected void loadImages() {
		try {
			credits.setIcon(new ImageIcon(ImageIO.read(new File("res/images/titles/credits.png"))));			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
