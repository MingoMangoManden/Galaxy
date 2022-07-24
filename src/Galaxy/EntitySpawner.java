package Galaxy;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Galaxy.entities.Entity;
import Galaxy.entities.EntityType;

public class EntitySpawner {
	
	GamePanel gp;
	EntityType[] types;
	long interval;
	
	Timer timer;
	
	ArrayList<Entity> entities = new ArrayList<>();
	
	public EntitySpawner(GamePanel gp, EntityType[] types, long interval) {
		this.gp = gp;
		this.types = types;
		this.interval = interval;
	}
	
	public void startSpawning() {
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				spawnRandomEntity();
			}
			
		}, 3000, interval);
	}
	
	private void spawnRandomEntity() {
		int width = 50;
		int height = 50;
		int x = (int) (Math.random()*Game.width) - width;
		int y = height;
		
		Entity entity = types[(int) (Math.random()*types.length)].getEntity();
		entity.setX(x);
		entity.setY(y);
		entity.setWidth(width);
		entity.setHeight(height);
		entities.add(entity);
	}
	
	public void stopSpawning() {
		timer.cancel();
	}

}
