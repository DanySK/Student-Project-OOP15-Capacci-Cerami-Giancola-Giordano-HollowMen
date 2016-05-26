package hollowmen.view.juls.dialog;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import hollowmen.view.juls.buttons.PaintedButton;

/**
 * The {@code NewGameDialog} class shows the user a simple message
 * in order to inform it about the imminent start of the game.
 * @author Juls
 *
 */
public class NewGameDialog extends MessageDialog {

	private static final long serialVersionUID = -8399745984113823014L;
	private JPanel messagePanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private PaintedButton no = new PaintedButton("NO");
	protected PaintedButton yes = new PaintedButton("YES");

	public NewGameDialog(Frame frame) {
		super(frame);
		super.addMessage("<html>You are going to start a New Adventure. <br> Are you ready?<html>");
		super.setTextForeground(Color.WHITE);
		
		
		messagePanel.setLayout(new GridLayout(1, 0, 0, 0));
		messagePanel.setBounds(60, 50, 340, 50);
		messagePanel.add(message);
		messagePanel.setOpaque(false);
		add(messagePanel);
	
		buttonPanel.setLayout(new GridLayout(1, 2, 40, 0));
		buttonPanel.setBounds(80, 100, 340, 58);
		buttonPanel.add(no);
		buttonPanel.add(yes);
		buttonPanel.setOpaque(false);
		add(buttonPanel);
		
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DifficultyMenu(frame);
				dispose();
			}
		});
		
		setVisible(true);
	}

	
	

}
