package hollowmen.view.juls.dialog;

import java.awt.Frame;
import java.awt.Image;
import java.util.Map;
import java.util.Optional;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import hollowmen.controller.ViewObserver;
import hollowmen.model.facade.InformationDealer;
import hollowmen.view.ViewImpl;
import hollowmen.view.juls.buttons.IconButton;
import hollowmen.view.juls.buttons.PaintedButton;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code GridDialog} abstract class will be extended from
 * those classes that need to create panels with
 * grid-organized buttons.
 * @author Juls
 *
 */
public abstract class GridDialog extends MenuDialog {

	private static final long serialVersionUID = -7697502946815508802L;
	protected JLabel statsBox = new JLabel();
	private ImageIcon portrait;
	protected IconButton button;
	protected String nameF, description;
	protected Icon icon;
	protected ViewObserver observer;
	protected ViewImpl view;
	protected Optional<Map<String, Double>> stats;
	private InformationDealer lastItem;
	protected PaintedButton close = new PaintedButton("CLOSE");
	protected JScrollBar scroll = new JScrollBar();
	protected JPanel gridPanel = PanelBuilder.getBuilder()
								.layout(20, 5, 3, 3)
								.bound(50, 100, 300, 320)
								.build();

	
	
	public GridDialog(Frame frame) {
		super(frame);
		this.add(gridPanel);
	}
	
	/**
	 * The {@code showStats} method shows on screen information about 
	 * what has been clicked before.
	 * @return - stats
	 */
	protected String showStats(Optional<Map<String, Double>> map) {
		String stats;
		stats = map.get().entrySet().toString();
		return stats;
	}
	
	/**
	 * This method is used to set the InformationDealer on which 
	 * some action (equip/unequip/buy/sell...) will be done.
	 * @param lastItem
	 */
	protected void setLastItem(InformationDealer lastItem) {
		this.lastItem = lastItem;
	}
	
	/**
	 * The method return the last InformationDealer clicked.
	 * @return lastItem
	 */
	protected InformationDealer getLastItem() {
		return lastItem;
	}
	
	/**
	 * This method draws a bigger representation of the image passed as parameter.
	 * @param image
	 */
	protected ImageIcon showImage(ImageIcon image) {
		Image i = image.getImage();
		Image scaled = i.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		portrait = new ImageIcon(scaled);
		return portrait;
	}
	
	protected void setButton(IconButton button) {
		this.button = button;
	}
	
	protected IconButton getButton() {
		return button;
	}
}
