package Galaxy.game.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Galaxy.engine.Game;
import Galaxy.engine.Keyboard;

public class Player extends Entity {
	
	String name;
	int x, y;
	int width, height;
	
	boolean isDead = false;
	Rectangle hitbox;
	
	double speed = Game.BASE_PLAYER_SPEED;
	ArrayList<Projectile> projectiles = new ArrayList<>();
	int laserCooldown = Game.BASE_PLAYER_LASER_COOLDOWN;
	int laserCooldownCounter = 0;
	
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
		// updating the hitbox
		hitbox = new Rectangle(x, y, width, height);
		
		boolean collidingWithLeftWall = (x <= 0);
		boolean collidingWithRightWall = (x+width >= Game.width);
		
		if (Keyboard.isKeyPressed(KeyEvent.VK_A))
			if (!collidingWithLeftWall)
				x -= speed;
		if (Keyboard.isKeyPressed(KeyEvent.VK_D))
			if (!collidingWithRightWall)
				x += speed;
		if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE))
			if (laserCooldownCounter == 0) {
				shoot();
				laserCooldownCounter = laserCooldown;
			} else
				laserCooldownCounter--;
		
		for (Entity projectile : projectiles) {
			projectile.update();
		}
		
	}
	
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(spaceship, x, y, width, height, null);
		
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
		projectiles.add(projectile);
		//System.out.println("pew");
		
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
