package Galaxy.game.events;

import java.util.ArrayList;

import Galaxy.game.Entity;

public abstract class EntityEvent {
	
	public abstract void trigger(Entity e);
	public abstract ArrayList<EntityListener> getListeners();
	public abstract void addListener(EntityListener listener);

}
