package Galaxy.entities;

public enum EntityType {
	
	PURPLE_SPACESHIP;
	
	EntityType() {
		
	}
	
	public Entity getEntity() {
		switch (this) {
			case PURPLE_SPACESHIP:
				return new PurpleSpaceShip(0, 0, 0, 0);
		}
		return null;
	}

}
