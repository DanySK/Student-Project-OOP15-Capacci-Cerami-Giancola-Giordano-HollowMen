package hollowmen.model.roomentity;

import java.util.Map;

/**
 * A little Strategy Interface that hold the set of Action an Actor can do
 * @author pigio
 *
 */
public interface ActionAllowed {
	
	/**
	 * 
	 * @return An unmodifiable Map of String and TypeAction
	 */
	public Map<String, Runnable> getActionAllowed();
}
