package Galaxy.entities;

import java.awt.Graphics2D;

public abstract class Entity {
	
	public abstract void update();
	public abstract void draw(Graphics2D g2);
	
	public abstract int getX();
	public abstract void setX(int x);
	public abstract int getY();
	public abstract void setY(int y);
	
	public abstract int getWidth();
	public abstract void setWidth(int width);
	public abstract int getHeight();
	public abstract void setHeight(int height);

}
