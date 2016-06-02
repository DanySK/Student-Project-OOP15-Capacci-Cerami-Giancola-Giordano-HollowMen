package hollowmen.view.juls.dialog;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hollowmen.enumerators.InputMenu;
import hollowmen.model.facade.InformationDealer;
import hollowmen.view.UtilitySingleton;
import hollowmen.view.juls.buttons.IconButton;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code Bestiary} class draws on screen a menu that contains
 * information about the enemy met.
 * 
 * @author Juls
 */
public class Bestiary extends GridDialog {

	private static final long serialVersionUID = 9026903745675122006L;
	private IconButton button;
	private JPanel buttonC = PanelBuilder.getBuilder()
							.layout(1, 0, 0, 0)
							.bound(150, 450, 150, 58)
							.addTo(close)
							.build();

	public Bestiary(Frame frame, Collection<InformationDealer> collection) {
		super(frame);
		this.loadImages();
		super.addTitle(title);
		this.populateBestiary(collection);
		this.add(buttonC);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UtilitySingleton.getInstance().getObserver().addInput(InputMenu.RESUME);
				dispose();
			}
		});
		
		this.setVisible(true);
	}

	/**
	 * The {@code populateBestiary} method fills the Bestiary menu with the enemies met.
	 * @param c - the collection of enemies met
	 */
	private void populateBestiary(Collection<InformationDealer> c) {
		c.stream()	
		.forEach(x -> {
			stats = x.getStat();
			nameF = x.getName();
			description = x.getDescription();
			icon = UtilitySingleton.getInstance().getStorage().get(nameF);
			button = new IconButton(icon);
			button.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) {
					showDescription(description);	
					showImage((ImageIcon) icon);
					statsBox.setText(showStats(stats));
					add(statsBox);	
				}
			});
			gridPanel.add(button);
		});
	}
	
	/**
	 * The {@code ShowDescription} method writes on screen the description of the mob.
	 * @param d - the description
	 */
	private void showDescription(String d) {
		JLabel label = new JLabel(d);
		label.setBounds(420, 470, 100, 50);
	}
	
	private void loadImages() {
		try {
			title.setIcon(new ImageIcon(ImageIO.read(new File("res/images/titles/bestiary.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
