package hollowmen.view.juls;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;


/**
 * The {@link PaintedButton} Class creates customized buttons.
 * The background of the buttons is loaded from the "res" folder.
 * 
 * @author Juls
 * @version 1.2
 * @since 12/05
 * 
 * Last Update: 12/05 19:25
 */
public class PaintedButton extends JButton implements CustomButton, ActionListener {
	
	private static final long serialVersionUID = -4128202845780339156L;
	private BufferedImage buttonBG;
	
	public PaintedButton(String text) {
		super(text);
		setPreferences();
	}
	
	@Override
	public void setPreferences() {
		setContentAreaFilled(false);
        setOpaque(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        addActionListener(this);
	}
	
	public void loadImages() {
		try {
			buttonBG = ImageIO.read(new File("res/images/littleButton.png"));
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		loadImages();
		g.drawImage(buttonBG, 0, 8, null);
		super.paintComponent(g); // must ALWAYS be at the end of the paintComponent() method
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(this.getText());
		
	}

}
