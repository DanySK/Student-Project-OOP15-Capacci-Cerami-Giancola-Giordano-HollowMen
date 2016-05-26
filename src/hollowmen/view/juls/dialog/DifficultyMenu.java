package hollowmen.view.juls.dialog;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hollowmen.view.juls.buttons.PaintedButton;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code DifficultyMenu} class allows the user
 * to choose the game difficulty.
 * 
 * @author Juls
 */
public class DifficultyMenu  extends MessageDialog {

	private static final long serialVersionUID = -8695951773300972039L;
	private JLabel title = new JLabel();
	private JLabel selection = new JLabel();
	private PaintedButton easy = new PaintedButton("EASY");
	private PaintedButton normal = new PaintedButton("NORMAL");
	private PaintedButton hard = new PaintedButton("HARD");
	private PaintedButton confirm = new PaintedButton("CONFIRM");
	private String name;
	private List<PaintedButton> buttonList = new ArrayList<>();
	private JPanel buttonsC = PanelBuilder.getBuilder()
							.layout(1, 3, 10, 0)
							.bound(15, 110, 470, 58)
							.addTo(easy)
							.addTo(normal)
							.addTo(hard)
							.build();
	private JPanel titleC = PanelBuilder.getBuilder()
							.layout(1, 0, 0, 0)
							.bound(115, 30, 270, 70)
							.addTo(title)
							.build();
	private JPanel confirmC = PanelBuilder.getBuilder()
							.layout(1, 0, 0, 0)
							.bound(175, 230, 150, 58)
							.addTo(confirm)
							.build();
	private JPanel difficulty = PanelBuilder.getBuilder()
							.layout(1, 0, 0, 0)
							.bound(228, 180, 100, 40)
							.addTo(selection)
							.build();

	public DifficultyMenu(Frame frame) {
		super(frame);
		this.setSize(500, 300);	
		this.loadImage();
		this.add(titleC);
		this.add(buttonsC);
		this.add(confirmC);
		this.add(difficulty);
		this.addToList();
		this.addMouseListener(dialogL);
		selection.setForeground(Color.WHITE);
		confirm.addActionListener(confirmL);
		confirm.setEnabled(false);
		
		this.setVisible(true);
	}
	
	private void addToList() {
		buttonList.add(easy);
		buttonList.add(normal);
		buttonList.add(hard);
		
		for(PaintedButton b : buttonList) {
			b.addActionListener(listener);
		}
	}
	
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			confirm.setEnabled(true);
			name = ((PaintedButton) e.getSource()).getText();
			selection.setText(name);
		}
	};
	
	ActionListener confirmL = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				//addInput(name)
				dispose();
			}
	};
	
	MouseListener dialogL = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			selection.setText("");
			confirm.setEnabled(false);
		}
	};
	
	private void loadImage() {
		try {
			title.setIcon(new ImageIcon(ImageIO.read(new File("res/images/titles/difficulty.png"))));			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
