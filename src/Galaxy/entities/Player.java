package Galaxy.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Galaxy.Game;
import Galaxy.Keyboard;

public class Player extends Entity {
	
	String name;
	int x, y;
	int width, height;
	
	double speed = Game.BASE_PLAYER_SPEED;
	ArrayList<PlayerProjectile> projectiles = new ArrayList<>();
	int laserCooldown = Game.BASE_PLAYER_LASER_COOLDOWN;
	int laserCooldownCounter = 0;
	
	BufferedImage spaceship = Game.SPACESHIP;
	
	public Player(String name, int x, int y, int width, int height) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
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
		
		for (PlayerProjectile projectile : projectiles) {
			projectile.update();
		}
		
	}
	
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(spaceship, x, y, width, height, null);
		
		for (PlayerProjectile projectile : projectiles) {
			if (projectile.liveTime > 0) {
				projectile.draw(g2);
				projectile.liveTime--;
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

}
