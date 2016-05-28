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
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hollowmen.model.facade.InformationDealer;
import hollowmen.view.ViewImpl;
import hollowmen.view.juls.buttons.IconButton;
import hollowmen.view.juls.buttons.PaintedButton;
import hollowmen.view.juls.panel.PanelBuilder;

public class Inventory extends TabbedDialog {

	private static final long serialVersionUID = 1157519982974148320L;
	private JLabel inventory = new JLabel();
	private JLabel body = new JLabel();
	private ImageIcon s1, s2, s3;
	
	private JLabel statsBox = new JLabel();
	private String nameF, lastItem;
	private Icon icon;
	private IconButton button;
	private ViewImpl view;
	private Optional<Map<String, Integer>> stats;

	private PaintedButton close = new PaintedButton("CLOSE"); //dentro buttonC
	private PaintedButton equip = new PaintedButton("EQUIP"); //dentro buttonC
	private PaintedButton unequip = new PaintedButton("UNEQUIP"); //dentro buttonC
	private IconButton head = new IconButton(); //dentro bodyPNorth
	private IconButton chest = new IconButton(); //dentro bodyPNorth
	private IconButton gloves = new IconButton(); //dentro bodyPCenter
	private IconButton rings = new IconButton(); //dentro bodyPCenter
	private IconButton legs = new IconButton(); //dentro bodyPCenter
	private IconButton boots = new IconButton(); //dentro bodyPSouth
	private IconButton weapon = new IconButton(); //dentro pEast
	private IconButton spell1 = new IconButton(s1); //dentro pEast
	private IconButton spell2 = new IconButton(s2); //dentro pEast
	private IconButton spell3 = new IconButton(s3); //dentro pEast
	
	private JPanel buttonC = PanelBuilder.getBuilder()
								.layout(1, 3, 30, 0)
								.bound(90, 450, 150, 58)
								.addTo(equip)
								.addTo(unequip)
								.addTo(close)
								.build();
	private JPanel bodyPNorth = PanelBuilder.getBuilder()
								.layout(2, 0, 0, 20)
								.bound(450, 70, 50, 50)
								.addTo(head)
								.addTo(chest)
								.build();
	private JPanel bodyPCenter = PanelBuilder.getBuilder()
								.layout(1, 3, 10, 0)
								.bound(430, 210, 50, 50)
								.addTo(gloves)
								.addTo(legs)
								.addTo(rings)
								.build();
	private JPanel bodyPSouth = PanelBuilder.getBuilder()
								.layout(1, 0, 0, 0)
								.bound(430, 300, 50, 50)
								.addTo(boots)
								.build();
	private JPanel pEast = PanelBuilder.getBuilder()
								.layout(4, 0, 0, 10)
								.bound(570, 40, 50, 50)
								.addTo(weapon)
								.addTo(spell1)
								.addTo(spell2)
								.addTo(spell3)
								.build();

	public Inventory(Frame frame, Collection<InformationDealer> collection) {
		super(frame);
		this.loadImages();
		this.addTitle(inventory);
		body.setBounds(420, 40, 130, 350);
		this.add(body);
		statsBox.setBounds(570, 270, 130, 140);
		this.add(statsBox);
		this.add(buttonC);
		this.add(bodyPNorth);
		this.add(bodyPCenter);
		this.add(bodyPSouth);
		this.add(pEast);
		
		equip.setEnabled(false);
		equip.addActionListener(paintedL);		
		unequip.setEnabled(false);
		unequip.addActionListener(paintedL);
		close.addActionListener(paintedL);
		this.addMouseListener(dialogL);

		this.populateTab(collection, "head", 1);
		this.populateTab(collection, "chest", 2);
		this.populateTab(collection, "gloves", 3);
		this.populateTab(collection, "rings", 4);
		this.populateTab(collection, "legs", 5);
		this.populateTab(collection, "boots", 6);
		this.populateTab(collection, "weapons", 7);
		this.populateTab(collection, "spells", 8);
		this.populateTab(collection, "consumables", 9);
		
		this.populateBody(collection, "head", head);
		this.populateBody(collection, "chest", chest);
		this.populateBody(collection, "gloves", gloves);
		this.populateBody(collection, "rings", rings);
		this.populateBody(collection, "legs", legs);
		this.populateBody(collection, "boots", boots);
		this.populateBody(collection, "weapons", weapon);
		
		this.setVisible(true);

	}
	
		public void populateTab(Collection<InformationDealer> c, String slot, int i) {
			c.stream()
			.filter(x -> x.getStat().equals(slot))	//TODO change getStat with getSlot
			//.filter(x -> x.getState().equals("unequiped"))
			.forEach(x -> {
				stats = x.getStat();
				nameF = x.getName();
				icon = view.getStorage().get(nameF);
				button = new IconButton(icon);
				button.addActionListener(new ActionListener() { // abilita l'equip
					public void actionPerformed(ActionEvent e) {
							equip.setEnabled(true);
							unequip.setEnabled(false);
							setButton(button);
							setLastItem(nameF);
							statsBox.setText(showStats(stats));
							add(statsBox);	
					}
				});
				tabbedPane.setTabComponentAt(i, button);
			});
		}
	
	public void populateBody(Collection<InformationDealer> c, String slot, IconButton button) {
		c.stream()
		.filter(x -> x.getStat().equals("equiped")) //TODO change getStat with getState - equip or not
		.forEach(x -> {
				x.getStat().equals(slot); //TODO change getStat with getSlot
				nameF = x.getName();
				icon = view.getStorage().get(nameF);
				button.setIcon(icon);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						unequip.setEnabled(true);
						equip.setEnabled(false);
						setButton(button);
						setLastItem(nameF);
					}
				});
		});
	}
		
	public void setButton(IconButton button) {
		this.button = button;
	}
	
	public IconButton getButton() {
		return button;
	}

	public String showStats(Optional<Map<String, Integer>> map) {
		String stats;
		stats = map.get().entrySet().toString();
		return stats;
	}

	public void setLastItem(String lastItem) {
		this.lastItem = lastItem;
	}
	
	public String getLastItem() {
		return lastItem;
	}
	
	public void loadImages() {
		try {
			inventory.setIcon(new ImageIcon(ImageIO.read(new File("res/images/titles/inventory.png"))));
			body.setIcon(new ImageIcon(ImageIO.read(new File("res/images/backgrounds/bodyTemplate.png"))));
			s1 = new ImageIcon("res/images/");
			s2= new ImageIcon("res/images/");
			s3= new ImageIcon("res/images/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	ActionListener paintedL = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			name = ((PaintedButton) e.getSource()).getText();
			if(name.equals("EQUIP")) {
				//addInput(EQUIP, getLastItem();
				getButton().setEnabled(false);
			} else if (name.equals("UNEQUIP")) {
				//addInput(UNEQUIP, getLastItem();
				remove(getButton()); //rimuove il bottone
				setButton(new IconButton()); // e ne aggiunge uno vuoto
			} else {
				dispose();
			}
		}
	};
	
	MouseListener dialogL = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			equip.setEnabled(false);
			unequip.setEnabled(false);
		}
	};
}