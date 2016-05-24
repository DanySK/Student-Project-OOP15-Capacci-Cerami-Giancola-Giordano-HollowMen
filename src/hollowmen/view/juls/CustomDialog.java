package hollowmen.view.juls;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hollowmen.view.SingletonFrame;

public class CustomDialog extends JDialog {

	private static final long serialVersionUID = 3277374770336705243L;
	private final JLabel background = new JLabel();
	private final JLabel text = new JLabel();
	private PaintedButton no = new PaintedButton("NO");
	private PaintedButton yes = new PaintedButton("YES");
	private JPanel container = new JPanel();
	private JPanel tContainer = new JPanel();
	
	public CustomDialog(Frame frame, String s) {
		super(SingletonFrame.getInstance(), s);
		loadBackground();
		setPreferences();	
		text.setText(s);
		arrangeComponent();
		setVisible(true);
	}
	
	private void setPreferences() {
		setContentPane(background);
		setSize(500, 200);
		setUndecorated(true);
		setLocationRelativeTo(SingletonFrame.getInstance());
		//setModal(true);
		text.setForeground(Color.WHITE);
		text.getFont().deriveFont(40f);
	}
	
	private void arrangeComponent() {
		tContainer.setBounds(60, 50, 340, 50);
		tContainer.setLayout(new GridLayout(1, 0, 0, 0));
		tContainer.add(text);
		tContainer.setOpaque(false);
		add(tContainer);
		
		container.setBounds(80, 100, 340, 58);	
		container.setLayout(new GridLayout(1, 2, 40, 0));
		container.add(no);
		container.add(yes);
		container.setOpaque(false);
		add(container);
	}
	
	private void loadBackground() {
		try {
			background.setIcon(new ImageIcon(ImageIO.read(new File("res/images/dialog.jpg"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
