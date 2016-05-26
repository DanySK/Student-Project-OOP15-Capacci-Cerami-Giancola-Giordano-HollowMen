package hollowmen.view.juls.panel;

import java.awt.Component;

import javax.swing.JPanel;

public interface JPanelBuilder {

	public JPanelBuilder layout(int rows, int columns, int verticalGap, int horizontalGap);
	
	public JPanelBuilder bound(int x, int y, int length, int height);
	
	public JPanelBuilder addTo(Component component);
	
	public JPanel build();
	
}