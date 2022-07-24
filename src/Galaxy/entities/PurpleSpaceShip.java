package Galaxy.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Galaxy.Game;

public class PurpleSpaceShip extends Entity {
	
	int x, y;
	int width, height;
	
	double speed = 2;
	int direction; // -1 or 1
	
	BufferedImage purpleSpaceship;
	
	public PurpleSpaceShip(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		int num = (Math.random() >= 0.5) ? 1 : -1;
		this.direction = num;
		
		purpleSpaceship = Game.PURPLE_SPACESHIP;
	}

	@Override
	public void update() {
		double gravity = 1;
		y += gravity;
		
		boolean collidingWithLeftWall = (x <= 0);
		boolean collidingWithRightWall = (x+width >= Game.width);
		
		if (collidingWithLeftWall)
			direction = 1;
		else if (collidingWithRightWall)
			direction = -1;
		
		x += direction*speed;
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(purpleSpaceship, x, y, width, height, null);
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
