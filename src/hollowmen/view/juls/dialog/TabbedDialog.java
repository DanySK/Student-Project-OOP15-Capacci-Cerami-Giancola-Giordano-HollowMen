package hollowmen.view.juls.dialog;

import java.awt.Frame;

import javax.swing.JTabbedPane;

/**
 * The {@code TabbedDialog} abstract class will be extended from
 * those classes that need to have some JTabbedPanes.
 * 
 * @author Juls
 */
public abstract class TabbedDialog extends GridDialog {

	private static final long serialVersionUID = 6838744487705502656L;
	protected JTabbedPane tabbedPane = new JTabbedPane();

	public TabbedDialog(Frame frame) {
		super(frame);
		this.addTabbedPane();
		this.add(tabbedPane);
		
	}
	
	protected void addTabbedPane() {
		tabbedPane.addTab("All", gridPanel);
		tabbedPane.addTab("Head", gridPanel);
		tabbedPane.addTab("Chest", gridPanel);
		tabbedPane.addTab("Legs", gridPanel);
		tabbedPane.addTab("Rings", gridPanel);
		tabbedPane.addTab("Weapons", gridPanel);
		tabbedPane.addTab("Consumables", gridPanel);
		//tabbedPane.addTab("Spells", gridPanel);
	}

}
