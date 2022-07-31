package Galaxy.game.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Galaxy.engine.Game;

public class PurpleSpaceShip extends Entity {
	
	int x, y;
	int width, height;
	
	boolean isDead = false;
	Rectangle hitbox;
	
	double speed = 2;
	int direction; // -1 or 1
	
	BufferedImage purpleSpaceship;
	
	public PurpleSpaceShip(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.hitbox = new Rectangle(x, y, width, height);
		
		int num = (Math.random() >= 0.5) ? 1 : -1;
		this.direction = num;
		
		purpleSpaceship = Game.PURPLE_SPACESHIP;
	}

	@Override
	public void update() {
		// updating the hitbox
		hitbox = new Rectangle(x, y, width, height);
		
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
		g2.setColor(Color.red);
		g2.draw(hitbox);
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
		return null;
	}

}
