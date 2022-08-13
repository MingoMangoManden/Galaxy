package Galaxy.engine;
import java.awt.Dimension;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Galaxy.game.GameManager;
import Galaxy.game.GamePanel;

public class Window extends JFrame {
	
	GamePanel gp;
	GameManager gm;
	
	public Window(String title, int width, int height) {
		setTitle(title);
		setSize(new Dimension(width, height));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		addKeyListener(new Keyboard());
		//setUndecorated(true);
		loadIconImage();
		
		gm = new GameManager();
		gp = new GamePanel(gm);
		add(gp);
		gp.startGameLoop();
	}
	
	private void loadIconImage() {
		String path = "/res/splash.jpg";
		
		try {
			setIconImage(ImageIO.read(getClass().getResource(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
