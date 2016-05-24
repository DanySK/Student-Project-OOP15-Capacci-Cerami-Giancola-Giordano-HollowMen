package hollowmen.view.juls;

import java.awt.Color;

import javax.swing.Icon;

public abstract class IconButton extends CustomButton {

	private static final long serialVersionUID = -1151945739902849112L;
	
	public IconButton() {
		super.addMouseListener(ma);
	}
	
	public IconButton(String text) {
		super(text);
		super.addMouseListener(ma);
	}
	
	public IconButton(Icon icon) {
		super(icon);
		super.addMouseListener(ma);
	}
	
	public void setPreferences() {
		this.setContentAreaFilled(true);
		this.setOpaque(true);
		this.setBorderPainted(true);
		this.setBackground(Color.BLACK);
	}

}
