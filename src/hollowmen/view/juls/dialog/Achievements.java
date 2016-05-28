package hollowmen.view.juls.dialog;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hollowmen.model.facade.InformationDealer;
import hollowmen.view.juls.buttons.IconButton;
import hollowmen.view.juls.buttons.PaintedButton;
import hollowmen.view.juls.panel.PanelBuilder;

public class Achievements extends GridDialog {


	private static final long serialVersionUID = -7374468384801676593L;
	private IconButton button;
	private String state;
	private ImageIcon star;
	private PaintedButton redeem = new PaintedButton("REDEEM");
	private JPanel buttonC = PanelBuilder.getBuilder()
							.layout(1, 2, 40, 0)
							.bound(50, 450, 150, 58)
							.addTo(close)
							.addTo(redeem)
							.build();
	
	public Achievements(Frame frame, Collection<InformationDealer> collection) {
		super(frame);
		this.loadImages();
		this.add(buttonC);
		this.populateAchievements(collection);
		redeem.setEnabled(false);
		redeem.addActionListener(paintedL);
		close.addActionListener(paintedL);
		this.addMouseListener(dialogL);
		this.setVisible(true);
	}
	
	private void populateAchievements(Collection<InformationDealer> c) {
		c.stream()	
		.forEach(x -> {
			state = x.getName(); //TODO change getName with getState
			nameF = x.getName();
			description = x.getDescription();
			button = new IconButton(star);
			button.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) {
					if(state.equals("locked") || state.equals("redeemed")) {
						redeem.setEnabled(false);
					}
					showDescription(description);
					setLastItem(x);
					showImage(star);
					statsBox.setText(showStats(stats));
					add(statsBox);	
				}
			});
			gridPanel.add(button);
		});
	}
	
	ActionListener paintedL = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			name = ((PaintedButton) e.getSource()).getText();
			if(name.equals("REDEEM")) {
				//addInput(REDEEM, getLastItem());
			}else {
				dispose();
			}
		}
	};

	private void showDescription(String d) {
		JLabel label = new JLabel(d);
		label.setBounds(420, 470, 100, 50);
	}
	
	MouseListener dialogL = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			redeem.setEnabled(false);;
		}
	};
	
	private void loadImages() {
		try {
			title.setIcon(new ImageIcon(ImageIO.read(new File("res/images/titles/achievements.png"))));
			star = new ImageIcon("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
