package Galaxy.game.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Galaxy.game.Velocity;

public class Asteroid extends Entity {
	
	int x, y;
	int width, height;
	
	boolean isDead = false;
	Rectangle hitbox;
	
	double speed;
	Velocity velocity;
	
	BufferedImage asteroid;
	
	public Asteroid(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	
	@Override
	public void update() {
		updateVelocity();
		applyVelocity();
	}
	
	private void updateVelocity() {
		
	}
	
	private void applyVelocity() {
		
	}
	
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWidth(int width) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void setDead(boolean isDead) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public ArrayList<Projectile> getSubEntities() {
		return null;
	}


}
