package Galaxy.engine;
import java.awt.Dimension;

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
		
		gm = new GameManager();
		gp = new GamePanel(gm);
		add(gp);
		gp.startGameLoop();
	}

}
