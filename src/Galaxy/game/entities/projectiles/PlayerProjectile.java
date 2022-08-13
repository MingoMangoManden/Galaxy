package Galaxy.game.entities.projectiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Galaxy.engine.Game;
import Galaxy.game.Projectile;
import Galaxy.game.Vector;

public class PlayerProjectile extends Projectile {
	
	int x, y;
	int width, height;
	double rotation = 0.0;
	
	boolean isDead = false;
	Rectangle hitbox;
	
	double speed = Game.BASE_LASER_SPEED;
	int liveTime = (int) (speed*6.9); // haha funny number
	Vector velocity;
	
	BufferedImage projectile;
	
	public PlayerProjectile(int x, int y) {
		this.width = Game.LASER_WIDTH;
		this.height = Game.LASER_HEIGHT;
		this.x = x - width/2;
		this.y = y;
		this.hitbox = new Rectangle(x, y, width, height);
		
		projectile = Game.SPACESHIP_LASER;
	}

	@Override
	public void update() {
		y -= speed;
		
		// updating the hitbox after position has been updated.
		hitbox = new Rectangle(x, y, width, height);
		liveTime--;
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.rotate(Math.toRadians(rotation));
		g2.drawImage(projectile, x, y, width, height, null);
		
		// hitbox
		if (Game.DEBUG_MODE) {
			g2.setColor(Color.green);
			g2.draw(hitbox);
		}
		g2.rotate(Math.toRadians(rotation*-1)); // resetting the g2's rotation for drawing other things
	}
	
	@Override
	public int getLiveTime() {
		return liveTime;
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

}
