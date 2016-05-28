package hollowmen.view.juls.dialog;

import java.awt.Frame;
import java.util.Collection;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import hollowmen.model.facade.InformationDealer;
import hollowmen.view.juls.buttons.IconButton;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code TabbedDialog} abstract class will be extended from
 * those classes that need to have some JTabbedPanes.
 * 
 * @author Juls
 */
public abstract class TabbedDialog extends GridDialog {

	private static final long serialVersionUID = 6838744487705502656L;
	protected JTabbedPane tabbedPane = new JTabbedPane();
	protected IconButton button;
	protected JPanel headP = PanelBuilder.getBuilder()
								.layout(20, 5, 3, 3)
								.bound(50, 100, 300, 320)
								.addTo(scroll)
								.build();
	protected JPanel chestP = PanelBuilder.getBuilder()
								.layout(20, 5, 3, 3)
								.bound(50, 100, 300, 320)
								.addTo(scroll)
								.build();
	protected JPanel glovesP = PanelBuilder.getBuilder()
								.layout(20, 5, 3, 3)
								.bound(50, 100, 300, 320)
								.addTo(scroll)
								.build();
	protected JPanel ringsP = PanelBuilder.getBuilder()
								.layout(20, 5, 3, 3)
								.bound(50, 100, 300, 320)
								.addTo(scroll)
								.build();
	protected JPanel legsP = PanelBuilder.getBuilder()
								.layout(20, 5, 3, 3)
								.bound(50, 100, 300, 320)
								.addTo(scroll)
								.build();
	protected JPanel bootsP = PanelBuilder.getBuilder()
								.layout(20, 5, 3, 3)
								.bound(50, 100, 300, 320)
								.addTo(scroll)
								.build();
	protected JPanel weaponsP = PanelBuilder.getBuilder()
								.layout(20, 5, 3, 3)
								.bound(50, 100, 300, 320)
								.addTo(scroll)
								.build();
	protected JPanel spellsP = PanelBuilder.getBuilder()
								.layout(20, 5, 3, 3)
								.bound(50, 100, 300, 320)
								.addTo(scroll)
								.build();
	protected JPanel consumP = PanelBuilder.getBuilder()
								.layout(20, 5, 3, 3)
								.bound(50, 100, 300, 320)
								.addTo(scroll)
								.build();
	protected JPanel inventoryP = PanelBuilder.getBuilder()
								.layout(20, 5, 3, 3)
								.bound(50, 100, 300, 320)
								.addTo(scroll)
								.build();
	protected JPanel shopP = PanelBuilder.getBuilder()
								.layout(20, 5, 3, 3)
								.bound(50, 100, 300, 320)
								.addTo(scroll)
								.build();
	
	
	public TabbedDialog(Frame frame) {
		super(frame);
		this.addTabbedPane();
		this.add(tabbedPane);
		
	}
	
	protected void addTabbedPane() {
		tabbedPane.addTab("head", headP);
		tabbedPane.addTab("chest", chestP);
		tabbedPane.addTab("gloves", glovesP);
		tabbedPane.addTab("rings", ringsP);
		tabbedPane.addTab("legs", legsP);
		tabbedPane.addTab("boots", bootsP);
		tabbedPane.addTab("weapons", weaponsP);
		tabbedPane.addTab("spells", spellsP);
		tabbedPane.addTab("consumables", consumP);
	}

	protected abstract void populateTab(Collection<InformationDealer> c, String s, JPanel panel);
	
	protected void setButton(IconButton button) {
		this.button = button;
	}
	
	protected IconButton getButton() {
		return button;
	}

	protected abstract void setButtonState(boolean b1, boolean b2);
	
}
