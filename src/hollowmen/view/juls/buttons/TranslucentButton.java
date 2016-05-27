package hollowmen.view.juls.buttons;

/**
 * The {@code TranslucentButton} abstract class allows to create buttons
 * with transparent/translucent background and no border.
 * @author Juls
 *
 */
public abstract class TranslucentButton extends CustomButton {

	private static final long serialVersionUID = 4450499357866877777L;
	
	public TranslucentButton() {
		super();
	}
	
	public TranslucentButton(String text) {
		super(text);
	}
	
	public void setPreferences() {
		this.setContentAreaFilled(false);
		this.setOpaque(false);
		this.setBorderPainted(false);
	}
	
	/**
	 * The {@code loadImages()} method will try to load the images
	 * needed to paint the button.
	 */
	public abstract void loadImages();

}
