package hollowmen.view.juls;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;

public abstract class CustomButton  extends JButton {
	
	private static final long serialVersionUID = -1680309409986913252L;
	protected boolean isOver;
	protected boolean isClicked;
	protected boolean isAvailable = true;
	protected MyMouseAdapter ma = new MyMouseAdapter();

	public CustomButton() {
		super.addMouseListener(ma);
	}
	
	public CustomButton(String text) {
		super(text);
		super.addMouseListener(ma);
	}
	
	public CustomButton(Icon icon) {
		super(icon);
		super.addMouseListener(ma);
	}

	public abstract void setPreferences();
	
	public void addMouseListener(MyMouseAdapter ma) {}
	
	@Override
	public void setEnabled(boolean b) {
		super.setEnabled(b);;
		if( b==false ) {
			isAvailable = false;
		}
	}
	
	protected class MyMouseAdapter extends MouseAdapter {
		
		public void mouseEntered(MouseEvent e) {
				isOver = true;
    	}
    	public void mouseExited(MouseEvent e) {
				isOver = false;
    	}
    	public void mouseClicked(MouseEvent e) {
				isClicked = true;
    	}
	}

}
