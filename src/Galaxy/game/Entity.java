package Galaxy.game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Entity {
	
	public abstract void update();
	public abstract void draw(Graphics2D g2);
	
	public abstract int getX();
	public abstract void setX(int x);
	public abstract int getY();
	public abstract void setY(int y);
	
	// public abstract Velocity getVelocity();
	// public abstract void setVelocity(Velocity velocity);
	
	public abstract double getRotation();
	public abstract void setRotation(double rotation);
	//public abstract void rotate(double degrees);
	
	public abstract int getWidth();
	public abstract void setWidth(int width);
	public abstract int getHeight();
	public abstract void setHeight(int height);
	
	public abstract double getSpeed();
	public abstract void setSpeed(double speed);
	
	public abstract Rectangle getBounds();
	
	public abstract boolean isDead();
	public abstract void setDead(boolean isDead);
	
	public abstract ArrayList<Projectile> getSubEntities();

}
