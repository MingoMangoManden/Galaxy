package Galaxy.game.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Galaxy.engine.Game;
import Galaxy.game.Enemy;
import Galaxy.game.GamePanel;
import Galaxy.game.Projectile;

public class PurpleSpaceShip extends Enemy {
	
	int x, y;
	int width, height;
	double rotation = 0.0;
	
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
		boolean collidingWithLeftWall = (x <= 0);
		boolean collidingWithRightWall = (x+width >= Game.width);
		
		if (collidingWithLeftWall)
			direction = 1;
		else if (collidingWithRightWall)
			direction = -1;
		
		x += direction*speed;
		
		double gravity = 2;
		y += gravity;
		
		// updating the hitbox only if the entity is shown on the screen
		boolean shownOnScreen = (y > 0);
		if (shownOnScreen) {
			hitbox = new Rectangle(x, y, width, height);
		} else {
			hitbox = new Rectangle();
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(purpleSpaceship, x, y, width, height, null);
		
		if (Game.DEBUG_MODE) {
			g2.setColor(Color.red);
			g2.draw(hitbox);
		}
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
		return null;
	}

	@Override
	public int getDirection() {
		return direction;
	}

	@Override
	public void setDirection(int direction) {
		this.direction = direction;
	}

}
