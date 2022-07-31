package Galaxy.game.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Galaxy.engine.Game;
import Galaxy.game.Velocity;

public class PlayerProjectile extends Projectile {
	
	int x, y;
	int width, height;
	
	boolean isDead = false;
	Rectangle hitbox;
	
	double speed = Game.BASE_LASER_SPEED;
	int liveTime = (int) (speed*6.9); // this is in updates ofc.
	Velocity velocity;
	
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
		// updating the hitbox
		if (!isDead)
			hitbox = new Rectangle(x, y, width, height);
			y -= speed;
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(projectile, x, y, width, height, null);
		liveTime--;
		// hitbox
		g2.setColor(Color.green);
		g2.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
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
