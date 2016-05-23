package hollowmen.view.ale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import hollowmen.controller.ViewObserver;
import hollowmen.utilities.Pair;

/**
 * The ScreenButton class is used to show on screen all the buttons that
 * the player can press during the game.
 * 
 * @author Alessia
 *
 */
public class ScreenButton extends JButton{
	private static final long serialVersionUID = -4490819627872969413L;
	
	public ScreenButton(ViewObserver observer, String command,List<Pair<String,JLabel>> storage){
		
		for(Pair<String,JLabel> elem: storage){
			if(elem.getX()==command){
				this.setIcon(elem.getY().getIcon());
				break;
			}
		}
		this.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				observer.addInput(command);
			}
		});
	}
	
}
