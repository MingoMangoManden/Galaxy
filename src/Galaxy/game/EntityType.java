package Galaxy.game;

import Galaxy.game.entities.PurpleSpaceShip;

public enum EntityType {
	
	PURPLE_SPACESHIP;
	
	public Enemy getEntity() {
		switch (this) {
			case PURPLE_SPACESHIP:
				return new PurpleSpaceShip(0, 0, 0, 0);
		}
		return null;
	}

}
