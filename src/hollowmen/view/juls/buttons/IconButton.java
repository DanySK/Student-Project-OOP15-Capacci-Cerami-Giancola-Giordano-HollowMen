package hollowmen.view.juls.buttons;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Icon;

/**
 * The {@code IconButton} abstract class determines a specialization
 * of {@link CustomButton}. It represents a button with icon.
 * @author Juls
 *
 */
public abstract class IconButton extends CustomButton {

	private static final long serialVersionUID = -1151945739902849112L;
	
	public IconButton() {
		super();
	}
	
	public IconButton(String text) {
		super(text);
	}
	
	public IconButton(Icon icon) {
		super(icon);
	}
	
	public void setPreferences() {
		this.setContentAreaFilled(false);
		this.setOpaque(false);
		this.setBorderPainted(true);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

}
