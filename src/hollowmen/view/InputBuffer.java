package hollowmen.view;

import hollowmen.controller.ViewObserver;

/**
 * This class was made cause View doesn't know how to take the ViewObserver
 * from the ViewImpl and since ViewImpl constructor has inputs i think about this simple Singleton
 * @author pigio
 *
 */
public class InputBuffer {

	
	private ViewObserver viewObserver;
	
	private InputBuffer() {};
	
	
	private static class Holder {
		public static InputBuffer INSTANCE = new InputBuffer();
	}
	
	public static InputBuffer getInstance() {
		return Holder.INSTANCE;
	}
	
	public ViewObserver getObserver() {
		return this.viewObserver;
	}
	
	public void setObserver(ViewObserver viewObs) {
		this.viewObserver = viewObs;
	}
	
}
