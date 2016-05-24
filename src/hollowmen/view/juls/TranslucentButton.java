package hollowmen.view.juls;


public abstract class TranslucentButton extends CustomButton {

	private static final long serialVersionUID = 4450499357866877777L;
	
	public TranslucentButton() {
		super.addMouseListener(ma);
	}
	
	public TranslucentButton(String text) {
		super(text);
		super.addMouseListener(ma);
	}
	
	public void setPreferences() {
		this.setContentAreaFilled(false);
		this.setOpaque(false);
		this.setBorderPainted(false);
	}

}
