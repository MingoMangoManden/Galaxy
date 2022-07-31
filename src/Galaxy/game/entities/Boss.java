package Galaxy.game.entities;

public abstract class Boss extends Entity {
	
	public abstract double getHealth();
	public abstract void setHealth(double amount);
	
	public abstract void damage(double amount);
	public abstract void heal(double amount);

}
