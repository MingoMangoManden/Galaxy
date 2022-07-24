package Galaxy;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import Galaxy.entities.Entity;
import Galaxy.entities.EntityType;
import Galaxy.entities.Player;

public class GamePanel extends JPanel implements Runnable {
	
	double updateTime = 1.0 / 120.0;
	
	boolean multiplayer = false;
	
	int playerWidth = 75;
	int playerHeight = 75;
	int startingPlayerX = (int) ((Game.width*0.5) - (playerWidth*0.5));
	int startingPlayerY = (int) (Game.height*0.83);
	
	Player PLAYER1 = new Player("PLAYER1", startingPlayerX, startingPlayerY, playerWidth, playerHeight);
	//Player PLAYER2 = new Player("PLAYER2", 0, 0);
	
	EntitySpawner spawner;
	ArrayList<Entity> entities = new ArrayList<>();
	
	public GamePanel() {
		setBackground(Color.black);
		entities.add(PLAYER1);
		//if (multiplayer)
			//entities.add(PLAYER2);
		this.spawner = new EntitySpawner(this, new EntityType[] {EntityType.PURPLE_SPACESHIP}, 1500);
	}
	
	public void startGameLoop() {
		Thread thread = new Thread(this);
		thread.start();
		spawner.startSpawning();
	}
	
	@Override
	public void run() {
		double lastTime = System.nanoTime();
		
		while(true) {
			double currentTime = System.nanoTime();
			double deltaTime = (currentTime - lastTime);
			
			boolean shouldUpdate = (deltaTime >= updateTime*1000000000);
			
			if (shouldUpdate) {
				update();
				repaint();
				lastTime = currentTime;
			}
		}
	}
	
	public void update() {
		for (Entity entity : entities) {
			entity.update();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		for (Entity entity : entities) {
			entity.draw(g2);
		}
		entities.addAll(spawner.entities);
		spawner.entities.clear();
		g2.dispose();
	}

}
