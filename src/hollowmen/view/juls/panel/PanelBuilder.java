package hollowmen.view.juls.panel;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class PanelBuilder{

	
	public PanelBuilder() {};
	
	
	public static JPanelBuilder getBuilder(){
		return new Builder();
	}
	
	private static class Builder extends JPanel implements JPanelBuilder {

		private static final long serialVersionUID = -3585215555536977182L;
		
		@Override
		public JPanelBuilder layout(int rows, int columns, int verticalGap, int horizontalGap) {
			super.setLayout(new GridLayout(rows, columns, verticalGap, horizontalGap));
			return this;
		}

		@Override
		public JPanelBuilder bound(int x, int y, int width, int height) {
			super.setBounds(x, y, width, height);
			return this;
		}

		@Override
		public JPanelBuilder addTo(Component component) {
			super.add(component);
			return this;
		}

		@Override
		public JPanel build() {
			super.setOpaque(false);
			return this;
		}
		
	}
}