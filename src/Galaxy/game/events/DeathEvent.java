package Galaxy.game.events;

import java.util.ArrayList;

import Galaxy.game.Entity;

public class DeathEvent extends EntityEvent {
	
	ArrayList<EntityListener> listeners = new ArrayList<>();

	@Override
	public void trigger(Entity e) {
		/*if (e.getType().equals(EntityType.PLAYER)) {
			new PlayerDeathEvent();
			return;
		}*/
		for (EntityListener listener : listeners) {
			listener.run(e);
		}
	}

	@Override
	public ArrayList<EntityListener> getListeners() {
		return listeners;
	}

	@Override
	public void addListener(EntityListener listener) {
		listeners.add(listener);
	}

}
