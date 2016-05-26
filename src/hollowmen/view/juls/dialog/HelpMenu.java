package hollowmen.view.juls.dialog;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hollowmen.view.juls.buttons.LeftArrow;
import hollowmen.view.juls.buttons.PaintedButton;
import hollowmen.view.juls.buttons.RightArrow;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code HelpMenu} shows the commands of the game. Extends {@link OptionDialog}.
 * 
 * @author Juls
 */
public class HelpMenu extends OptionDialog {

	private static final long serialVersionUID = 8558594124420531160L;
	private final JLabel title = new JLabel();
	private final JLabel sheet1 = new JLabel();
	private final JLabel sheet2 = new JLabel();

	private LeftArrow left = new LeftArrow(); // on panel2
	private RightArrow right = new RightArrow(); // on panel1
	private PaintedButton close = new PaintedButton("CLOSE");
	

	public HelpMenu(Frame frame) {
		super(frame);
		loadImages();
		
		JPanel panel = PanelBuilder.getBuilder()
				.layout(1, 0, 0, 0)
				.bound(250, 15, 145, 70)
				.addTo(title)
				.build();
				add(panel);
		
		JPanel buttonC = PanelBuilder.getBuilder()
				.layout(1, 0, 0, 0)
				.bound(240, 430, 150, 58)
				.addTo(close)
				.build();
				add(buttonC);	
		
		JPanel panelSheet1 = PanelBuilder.getBuilder()
				.layout(1, 0, 0, 0)
				.bound(150, 80, 350, 350)
				.addTo(sheet1)
				.build();
				add(panelSheet1);

		JPanel arrowC1 = PanelBuilder.getBuilder()
				.layout(1, 0, 0, 0)
				.bound(500, 430, 40, 40)
				.addTo(right)
				.build();
				add(arrowC1);
		
		JPanel panelSheet2 = PanelBuilder.getBuilder()
				.layout(1, 0, 0, 0)
				.bound(150, 80, 350, 350)
				.addTo(sheet2)
				.build();
		
		JPanel arrowC2 = PanelBuilder.getBuilder()
				.layout(1, 0, 0, 0)
				.bound(500, 435, 40, 40)
				.addTo(left)
				.build();	
		
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}		
		});		
		
		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(panelSheet1);
				add(panelSheet2);
				remove(arrowC1);
				add(arrowC2);
				repaint();
		        revalidate();
			}
		});
		
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(panelSheet2);
				add(panelSheet1);
				remove(arrowC2);
				add(arrowC1);
				repaint();
		        revalidate();
			}
		});
		
		setVisible(true);
	}
	
	@Override
	protected void loadImages() {
		try {
			title.setIcon(new ImageIcon(ImageIO.read(new File("res/images/titles/help.png"))));
			sheet1.setIcon(new ImageIcon(ImageIO.read(new File("res/images/titles/helpSheet1.png"))));
			sheet2.setIcon(new ImageIcon(ImageIO.read(new File("res/images/titles/helpSheet2.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
