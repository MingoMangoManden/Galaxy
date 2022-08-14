package Galaxy.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Galaxy.engine.Game;
import Galaxy.engine.Keyboard;
import Galaxy.game.entities.Player;
import Galaxy.game.ui.HoveringText;

public class GameManager {
	
	boolean multiplayer = false;
	
	int playerWidth = 75;
	int playerHeight = 75;
	int startingPlayerX = (int) ((Game.width*0.5) - (playerWidth*0.5));
	int startingPlayerY = (int) (Game.height*0.83);
	
	Player PLAYER1 = new Player("PLAYER1", startingPlayerX, startingPlayerY, playerWidth, playerHeight);
	//Player PLAYER2 = new Player("PLAYER2", 0, 0);
	
	int tutorialTextCooldown = 0; // in updates
	HoveringText tutorialText = new HoveringText("Moving: A & D", tutorialTextCooldown);
	HoveringText tutorialText2 = new HoveringText("Shooting: SPACE", tutorialTextCooldown);
	
	EntitySpawner spawner;
	ArrayList<Entity> entities = new ArrayList<>();
	
	public static int currentStage = 1;
	public static int wave = 1;
	//int wavesLeft = 5;
	public static int score = 0;
	
	int nextStageUpdatesShown = 120;
	int nextStageCounter = 0;
	
	public static float difficulty = 1.0f;
	
	GameState gameState = GameState.MENU;

	int pauseCooldown = 30; // you can pause/unpause every second.
	int pauseCooldownCounter = pauseCooldown;
	
	Font standardFont = Game.FONT;//new Font("Times New Roman", Font.PLAIN, 12);
	
	public GameManager() {
		//entities.add(PLAYER1);
		//if (multiplayer)
			//entities.add(PLAYER2);
		spawner = new EntitySpawner(new EntityType[] {EntityType.PURPLE_SPACESHIP}, 1500);
	}
	
	public void startGame() {
		System.out.println("game started");
		gameState = GameState.PLAYING;
		spawner.startSpawning();
	}
	
	public void update(double deltaTime) {
		if (gameState == GameState.MENU) {
			for (int i = 0; i < KeyEvent.KEY_LAST; i++) {
				if (Keyboard.isKeyPressed(i)) {
					try {
						Thread.sleep(100); // 1 tenth of a second
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// start at the tutorial, to make sure the player understands the game
					gameState = GameState.TUTORIAL;
				}
			}
		}
		
		if (gameState == GameState.TUTORIAL) {
			tutorialText.update();
			tutorialText2.update();
			PLAYER1.update();
			if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) { // actually starting the game
				startGame();
			}
		}
		
		if (gameState == GameState.PLAYING) {
			PLAYER1.update();
			checkCollision(PLAYER1);
			
			for (Entity entity : entities) {
				if (!entity.isDead())
					checkCollision(entity);
					entity.update();
			}
			
			if (score >= 15*currentStage) {
				//advanceToNextStage();
				currentStage++;
				difficulty += 0.5f;
			}
			
			if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)) {
				if (pauseCooldownCounter <= 0) {
					System.out.println("game paused");
					gameState = GameState.PAUSED;
					spawner.setPaused(true);
					pauseCooldownCounter = pauseCooldown;
				}
			}
			pauseCooldownCounter--;
		}
		
		if (gameState == GameState.PAUSED) {
			if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE) || Keyboard.isKeyPressed(KeyEvent.VK_A) || Keyboard.isKeyPressed(KeyEvent.VK_D) || Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
				if (pauseCooldownCounter <= 0) {
					System.out.println("game resumed");
					gameState = GameState.PLAYING;
					spawner.setPaused(false);
					pauseCooldownCounter = pauseCooldown;
				}
			}
			pauseCooldownCounter--;
		}
	}
	
	public void drawGame(Graphics2D g2) {
		
		switch (gameState) {
			case MENU -> {
				g2.setColor(Color.white);
				
				// draw title
				String title = "GALAXY GAME";
				Font font = standardFont.deriveFont(45f);
				g2.setFont(font);
				g2.drawString(title, getCenteredX(title, g2.getFontMetrics(font)), 100);
				
				// draw other thing
				String text = "Press any key to continue";
				font = standardFont.deriveFont(25f);
				g2.setFont(font);
				g2.drawString(text, getCenteredX(text, g2.getFontMetrics(font)), 250);
			}
			case TUTORIAL -> {
				// tutorial text
				if (tutorialText.isDisplayed()) {
					String text = tutorialText.getText();
					Font font = standardFont.deriveFont(25f);
					g2.setFont(font);
					g2.setColor(Color.white);
					g2.drawString(text, getCenteredX(text, g2.getFontMetrics(font)), (int) (Game.height*0.71));
				}
				// tutorial text 2
				if (tutorialText2.isDisplayed()) {
					String text = tutorialText2.getText();
					Font font = standardFont.deriveFont(25f);
					g2.setFont(font);
					g2.setColor(Color.white);
					g2.drawString(text, getCenteredX(text, g2.getFontMetrics(font)), (int) (Game.height*0.75));
				}
				
				PLAYER1.draw(g2);
			}
			case PLAYING -> {
				PLAYER1.draw(g2);
				
				for (Entity entity : entities) {
					if (!entity.isDead())
						entity.draw(g2);
				}
				entities.addAll(spawner.entities);
				spawner.entities.clear();
				
				// drawing gui
				g2.setColor(Color.white);
				
				
				// HI DUMMY PLEASE FIX THANKSSF
				boolean shouldDisplayStageInfo = false;//(nextStageCounter != 0);
				if (shouldDisplayStageInfo) {
					g2.setFont(standardFont.deriveFont(200f));
					g2.drawString("Stage " + currentStage, 150, 300); // center
					g2.setFont(standardFont.deriveFont(50f));
					g2.drawString("Wave " + wave, 150, 350); // center
				}
				
				g2.setFont(standardFont.deriveFont(25f));
				g2.drawString("STAGE: " + currentStage, 5, 25);
				g2.drawString("SCORE: " + score, 5, 50);
			}
			case PAUSED -> {
				PLAYER1.draw(g2);
				
				for (Entity entity : entities) {
					if (!entity.isDead())
						entity.draw(g2);
				}
				entities.addAll(spawner.entities);
				spawner.entities.clear();
				
				// drawing gui
				g2.setColor(Color.white);
				
				g2.setFont(standardFont.deriveFont(25f));
				g2.drawString("STAGE: " + currentStage, 5, 25);
				g2.drawString("SCORE: " + score, 5, 50);
				
				String text = "PAUSED";
				Font font = standardFont.deriveFont(50f);
				g2.setFont(font);
				g2.setColor(Color.white);
				g2.drawString(text, getCenteredX(text, g2.getFontMetrics(font)), getCenteredY(text, g2.getFontMetrics(font)));
			}
		}
	}
	
	private int getCenteredX(String text, FontMetrics fm) {
		return (Game.width-fm.stringWidth(text)) / 2;
	}
	
	private int getCenteredY(String text, FontMetrics fm) {
		return (Game.height-fm.getHeight()) / 2;
	}
	
	private void checkCollision(Entity entity) {
		for (Entity i : entities) {
			if (i != entity) { // Making sure it doesn't check if it's colliding with itself.
				if (i.getBounds().intersects(entity.getBounds())) {
					if (i instanceof Player) {
						i.setDead(true);
					}
				}
				
				// sub entities of entity
				if (entity.getSubEntities() != null) {
					for (Projectile projectile : entity.getSubEntities()) {
						if (!projectile.isDead() && !i.isDead()) {
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
	
	private void exterminate(Entity e) {
		e.setDead(true);
		score++;
	}
	
	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

}
