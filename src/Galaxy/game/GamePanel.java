package Galaxy.game;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import Galaxy.engine.Game;
import Galaxy.game.entities.Entity;
import Galaxy.game.entities.Player;
import Galaxy.game.entities.Projectile;

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
	
	String stage = "1";
	int score = 0;
	
	GameState gameState = GameState.PLAYING;
	
	public GamePanel() {
		setBackground(Color.black);
		//entities.add(PLAYER1);
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
		PLAYER1.update();
		checkCollision(PLAYER1);
		
		for (Entity entity : entities) {
			if (!entity.isDead())
				checkCollision(entity);
				entity.update();
		}
	}
	
	public void checkCollision(Entity entity) {
		for (Entity i : entities) {
			if (i != entity) { // Making sure it doesn't check if it's colliding with itself.
				if (i.getBounds().intersects(entity.getBounds())) {
					if (i instanceof Player) {
						i.setDead(true);
					}
				}
				
				if (entity.getSubEntities() != null) {
					for (Projectile projectile : entity.getSubEntities()) {
						if (!projectile.isDead()) {
							if (i.getBounds().intersects(projectile.getBounds())) {
								exterminate(i);
								projectile.setDead(true);
							}
						}
					}
				}
				
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		int fontSize = 45;
		int size = (int) (fontSize*3.5);
		g2.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
		String title = "GALAXY GAME";
		
		switch (gameState) {
			case MENU -> {
				g2.setColor(Color.white);
				g2.drawString(title, Game.width/2-(size), 50);
			}
			case PLAYING -> {
				PLAYER1.draw(g2);
				
				for (Entity entity : entities) {
					if (!entity.isDead())
						entity.draw(g2);
				}
				entities.addAll(spawner.entities);
				spawner.entities.clear();
				
				// Painting UI.
				g2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
				g2.setColor(Color.white);
				
				g2.drawString("STAGE: " + stage, 5, 25);
				g2.drawString("SCORE: " + score, 5, 50);
			}
			default -> {
				System.out.println("hesfdsafd");
			}
		}
		
		g2.dispose();
	}
	
	public void exterminate(Entity e) {
		e.setDead(true);
		score++;
	}
	
	public void removeAllTempEntities() {
		
	}
	
}
