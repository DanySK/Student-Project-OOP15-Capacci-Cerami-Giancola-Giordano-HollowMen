package hollowmen.view.juls.dialog;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hollowmen.enumerators.ClassType;
import hollowmen.view.juls.buttons.IconButton;
import hollowmen.view.juls.buttons.PaintedButton;
import hollowmen.view.juls.panel.PanelBuilder;

public class ClassChoice extends OptionDialog {

	private static final long serialVersionUID = 402941805788593572L;
	private ImageIcon w = new ImageIcon("res/images/character/hero.png");
	private ImageIcon m = new ImageIcon("res/images/character/mage.png");
	private ImageIcon a = new ImageIcon("res/images/character/assassin.png");
	private JLabel title = new JLabel();
	private String selection;
	private IconButton warrior = new IconButton("WARRIOR", w);
	private IconButton mage = new IconButton("MAGE", m);
	private IconButton assassin = new IconButton("ASSASSIN", a);
	private PaintedButton close = new PaintedButton("CLOSE");
	private PaintedButton select = new PaintedButton("SELECT");
	private JPanel classC = PanelBuilder.getBuilder()
							.layout(1, 3, 50, 0)
							.bound(100, 160, 450, 200)
							.addTo(warrior)
							.addTo(mage)
							.addTo(assassin)
							.build();
	private JPanel buttonC = PanelBuilder.getBuilder()
							.layout(1, 2, 120, 0)
							.bound(100, 400, 420, 58)
							.addTo(close)
							.addTo(select)
							.build();

	
	public ClassChoice(Frame frame) {
		super(frame);
		this.loadImages();
		add(title);
		title.setBounds(50, 30, 300, 60);
		this.add(classC);
		this.add(buttonC);
		close.addActionListener(listener);
		select.addActionListener(listener);
		warrior.addActionListener(classL);
		mage.addActionListener(classL);
		assassin.addActionListener(classL);
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				select.setEnabled(false);
			}
		});
		
		this.setVisible(true);
	}
	
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			name = ((PaintedButton) e.getSource()).getText();
			if(name.equals("SELECT")) {
				//view.addInput(ClassType.valueOf(selection));
			} else {
				dispose();
			}
		}
	};
	
	ActionListener classL = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			selection = ((IconButton) e.getSource()).getText();
			if(selection.equals("WARRIOR")) {
				select.setEnabled(true);
			} else {
				select.setEnabled(false);
			}
			
		}
	};

	@Override
	protected void loadImages() {
		try {
			title.setIcon(new ImageIcon(ImageIO.read(new File("res/images/titles/class.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
