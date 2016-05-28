package hollowmen.view.juls.buttons;

import java.awt.Color;
import java.util.Map;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.Icon;

import hollowmen.model.facade.InformationDealer;

/**
 * The {@code IconButton} class determines a specialization
 * of {@link CustomButton}. It represents a button with icon.
 * @author Juls
 *
 */
public class IconButton extends CustomButton {

	private static final long serialVersionUID = -1151945739902849112L;
	
	public IconButton() {
		super();
		this.setPreferences();
	}
	
	public IconButton(String text) {
		super(text);
	}
	
	public IconButton(Icon icon) {
		super(icon);
	}
	
	public IconButton(Icon icon, InformationDealer dealer) {
		super(icon);
	}
	
	public void setPreferences() {
		this.setContentAreaFilled(false);
		this.setOpaque(false);
		this.setBorderPainted(true);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

}
