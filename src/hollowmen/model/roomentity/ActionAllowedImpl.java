package hollowmen.model.roomentity;

import java.util.HashMap;
import java.util.Map;

public class ActionAllowedImpl implements ActionAllowed{

	private Map<String, Runnable> actionMap;
	
	public ActionAllowedImpl() {
		this.actionMap = new HashMap<>();
	}
	
	@Override
	public Map<String, Runnable> getActionAllowed() {
		return this.actionMap;
	}

}
