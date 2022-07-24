package Galaxy.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Galaxy.Game;

public class PlayerProjectile extends Entity {
	
	int x, y;
	int width, height;
	
	double speed = Game.BASE_LASER_SPEED;
	int liveTime = (int) (speed*6.9); // this is in updates ofc.
	Velocity velocity;
	
	BufferedImage projectile;
	
	public PlayerProjectile(int x, int y) {
		this.width = Game.LASER_WIDTH;
		this.height = Game.LASER_HEIGHT;
		this.x = x - width/2;
		this.y = y;
		
		projectile = Game.SPACESHIP_LASER;
	}

	@Override
	public void update() {
		y -= speed;
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(projectile, x, y, width, height, null);
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
