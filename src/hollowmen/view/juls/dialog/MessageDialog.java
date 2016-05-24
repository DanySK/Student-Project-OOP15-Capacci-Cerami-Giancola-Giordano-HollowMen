package hollowmen.view.juls.dialog;

import java.awt.Color;
import java.awt.Frame;


import hollowmen.view.SingletonFrame;

/**
 * The {@code MessageDialog} abstract class represents a dialog showing
 * the user a message.
 * @author Juls
 *
 */
public abstract class MessageDialog extends CustomDialog {

	private static final long serialVersionUID = 4641997191314208379L;

	public MessageDialog(Frame frame) {
		super(frame);		
		this.setPreferences();	
	}
	
	protected void setPreferences() {
		this.setSize(500, 200);
		this.setUndecorated(true);
		this.setLocationRelativeTo(SingletonFrame.getInstance());
		this.setModal(true);
	}
	
	protected void setTextForeground(Color color) {
		message.setForeground(color);
	}
	
	protected void addMessage(String text) {
		message.setText(text);
	}


}
