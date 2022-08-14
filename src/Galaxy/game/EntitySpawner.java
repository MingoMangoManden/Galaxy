package Galaxy.game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Galaxy.engine.Game;

public class EntitySpawner {
	
	EntityType[] types;
	long interval;
	
	Timer timer;
	boolean isPaused = false;
	
	ArrayList<Entity> entities = new ArrayList<>();
	
	public EntitySpawner(EntityType[] types, long interval) {
		this.types = types;
		this.interval = interval;
	}
	
	public void startSpawning() {
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if (!isPaused) {
					spawnRandomEntity();
					GameManager.wave++;
				}
			}
			
		}, 5000, 5000);
	}
	
	private void spawnRandomEntity() {
		int width = 50;
		int height = 50;
		int x = (int) (Math.random()*Game.width) - width;
		int y = 0-height;
		
		int direction = 1; // direction of all the spawning entities
		for (int i = 0; i < GameManager.wave*GameManager.currentStage; i++) {
			Enemy entity = types[(int) (Math.random()*types.length)].getEntity();
			entity.setX(x-i*50);
			entity.setY(y-i*50);
			entity.setWidth(width);
			entity.setHeight(height);
			entity.setDirection(direction);
			//entity.setSpeed(entity.getSpeed()*GamePanel.difficulty);
			entities.add(entity);
		}
		
		// reverse
		direction = -1;
		for (int i = 0; i < GameManager.wave*GameManager.currentStage; i++) {
			Enemy entity = types[(int) (Math.random()*types.length)].getEntity();
			entity.setX(x-i*50*direction);
			entity.setY(y-i*50);
			entity.setWidth(width);
			entity.setHeight(height);
			entity.setDirection(direction);
			//entity.setSpeed(entity.getSpeed()*GamePanel.difficulty);
			System.out.println("new enemy spawned with speed " + entity.getSpeed());
			entities.add(entity);
		}
		
	}
	
	public void setPaused(boolean value) {
		this.isPaused = value;
	}
	
	public void stopSpawning() {
		timer.cancel();
	}

}
