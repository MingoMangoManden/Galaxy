package Galaxy.engine;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game {
	
	public static final String title = "Galaxy Game inspired by C64 version";
	public static final int width = 1000;
	public static final int height = 800;
	
	public static final String RESOURCE_PATH = "/res/";
	public static Font FONT;
	
	public static final boolean DEBUG_MODE = false;
	
	public static final double BASE_PLAYER_SPEED = 5;
	
	public static final int BASE_PLAYER_LASER_COOLDOWN = 35;
	public static final int BASE_LASER_SPEED = 10; // when changing this from stage to stage, remember to also change the live time
	public static final int LASER_WIDTH = 50;
	public static final int LASER_HEIGHT = 50;
	
	public static final String PURPLE_SPACESHIP_PATH = "/res/purple_spaceship.png";
	public static final String SPACESHIP_PATH = "/res/spaceship.png";
	
	public static BufferedImage SPACESHIP;
	public static BufferedImage SPACESHIP_LASER;
	public static BufferedImage PURPLE_SPACESHIP;
	
	Window window;
	
	public Game() {
		loadAssets();
		window = new Window(title, width, height);
	}
	
	public void start() {
		window.setVisible(true);
	}
	
	public void loadAssets() {
		try {
			Game.SPACESHIP_LASER = ImageIO.read(getClass().getResource("/res/player_projectile.png"));
			Game.PURPLE_SPACESHIP = ImageIO.read(getClass().getResource("/res/purple_spaceship.png"));
			loadFont();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadFont() {
		String fontPath = "/res/VCR_OSD_Mono.ttf";
	    File file = new File(getClass().getResource(fontPath).getFile());

		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, file);
			font = font.deriveFont(25f);
			Game.FONT = font;
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
