package Galaxy.game.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Galaxy.engine.Game;
import Galaxy.engine.Keyboard;
import Galaxy.game.Entity;
import Galaxy.game.GamePanel;
import Galaxy.game.Projectile;
import Galaxy.game.entities.projectiles.PlayerProjectile;

public class Player extends Entity {
	
	String name;
	int x, y;
	int width, height;
	double rotation = 0.0;
	
	boolean isDead = false;
	Rectangle hitbox;
	
	double speed = Game.BASE_PLAYER_SPEED;
	ArrayList<Projectile> projectiles = new ArrayList<>();
	
	int laserCooldown = Game.BASE_PLAYER_LASER_COOLDOWN;
	int laserCooldownCounter = 0;
	
	// spinning shit WOO
	boolean spinning = false;
	int spinCooldown = (int) (GamePanel.fps * 0.5); // every ½ second
	int spinCooldownCounter = 0;
	
	double spinningSpeed = 5;
	double spinningDegrees = 360.0;
	
	BufferedImage spaceship = Game.SPACESHIP;
	
	public Player(String name, int x, int y, int width, int height) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.hitbox = new Rectangle(x, y, width, height);
		
		try {
			spaceship = ImageIO.read(getClass().getResource("/res/spaceship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update() {
		
		boolean collidingWithLeftWall = (x <= 0);
		boolean collidingWithRightWall = (x+width >= Game.width);
		
		// moving
		if (Keyboard.isKeyPressed(KeyEvent.VK_A))
			if (!collidingWithLeftWall)
				x -= speed;
		if (Keyboard.isKeyPressed(KeyEvent.VK_D))
			if (!collidingWithRightWall)
				x += speed;
		
		// pew pew
		if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE))
			if (laserCooldownCounter == 0) {
				shoot();
				laserCooldownCounter = laserCooldown;
			} else {
				laserCooldownCounter--;
			}
		
		// spinning
		if (spinning) {
			if (rotation >= spinningDegrees) {
				spinning = false;
				speed = Game.BASE_PLAYER_SPEED;
				rotation = 0.0;
			}
		}
		if (Keyboard.isKeyPressed(KeyEvent.VK_S)) {
			if (spinCooldownCounter == 0) {
				spin();
				spinCooldownCounter = spinCooldown;
			} else {
				spinCooldownCounter--;
			}
		}
		
		// updating the hitbox after the position and stuff has been updated.
		hitbox = new Rectangle(x, y, width, height);
		
		for (Entity projectile : projectiles) {
			projectile.update();
		}
		
	}
	
	@Override
	public void draw(Graphics2D g2) {
		// spinning
		if (spinning) {
			if (rotation == 360.0 || rotation == -360) // normalizing the rotation
				rotation = 0.0;
			rotation += spinningSpeed;
		}
		
		// drawing player with rotation
		// making sure it's rotating around itself
		int xCenter = (int) (x + (width*0.5));
		int yCenter = (int) (y + (height*0.5));
		
		g2.rotate(Math.toRadians(rotation), xCenter, yCenter); // rotating
		g2.drawImage(spaceship, x, y, width, height, null); // drawing
		g2.rotate(Math.toRadians(rotation*-1), xCenter, yCenter); // reversing the rotation
		
		// hitbox
		if (Game.DEBUG_MODE) {
			g2.setColor(Color.green);
			g2.draw(hitbox);
		}
		
		for (Projectile projectile : projectiles) {
			if (projectile.getLiveTime() > 0 && !projectile.isDead()) {
				projectile.draw(g2);
			}
		}
	}
	
	private void shoot() {
		// create laser projectile
		int projectileX = (int) (x+width*0.5);
		int projectileY = (int) (y - Game.LASER_HEIGHT*0.5);
		PlayerProjectile projectile = new PlayerProjectile(projectileX, projectileY);
		//int range = 40;
		//double randomRotation = Math.floor(Math.random()*range);
		//randomRotation -= range*0.5;
		//projectile.setRotation(randomRotation);
		//System.out.println(randomRotation);
		projectiles.add(projectile);
		//System.out.println("pew");
		
	}
	
	private void spin() {
		spinning = true;
		speed = speed*1.5;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public double getRotation() {
		return rotation;
	}
	
	@Override
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}
	
	@Override
	public double getSpeed() {
		return speed;
	}
	
	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	@Override
	public Rectangle getBounds() {
		return hitbox;
	}

	@Override
	public boolean isDead() {
		return isDead;
	}

	@Override
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	@Override
	public ArrayList<Projectile> getSubEntities() {
		return projectiles;
	}

}
