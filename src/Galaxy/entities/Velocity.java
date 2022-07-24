package Galaxy.entities;

public class Velocity {
	
	double x, y;
	
	public Velocity(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Velocity() {
		this.x = 0;
		this.y = 0;
	}
	
	public void normalize(double deltaTime) {
		Velocity velocity = this;
		
		// positive
		if (velocity.x > 0 && velocity.x < 0.75) { // if between 0.0 and 0.75
			velocity.x = 0; // reset it
		}
		
		// negative
		if (velocity.x < 0 && velocity.x > -0.75) { // if between 0.0 and 0.75
			velocity.x = 0; // reset it
		}
		
		// positive
		if (velocity.y > 0 && velocity.y < 0.75) { // if between 0.0 and 0.75
			velocity.y = 0; // reset it
		}
		
		// negative
		if (velocity.y < 0 && velocity.y > -0.75) { // if between 0.0 and 0.75
			velocity.y = 0; // reset it
		}
		
		double goofiness = 0.8;
		
		if (velocity.x != 0) {
			velocity.x *= goofiness * deltaTime*2;
		}
		if (velocity.y != 0) {
			velocity.y *= goofiness * deltaTime*2;
		}
	}
	
	public void add(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	public void sub(double x, double y) {
		this.x -= x;
		this.y -= y;
	}
	
	public void mul(double x, double y) {
		this.x *= x;
		this.y *= y;
	}
	
	public void div(double x, double y) {
		this.x /= x;
		this.y /= y;
	}

}
