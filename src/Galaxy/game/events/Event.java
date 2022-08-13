package Galaxy.game.events;

import java.util.ArrayList;

public abstract class Event {
	
	//public abstract void trigger();
	public abstract ArrayList<EntityListener> getListeners();
	public abstract void addListener(Listener listener);

}
