package Galaxy;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {
	
	GamePanel gp;
	
	public Window(String title, int width, int height) {
		setTitle(title);
		setSize(new Dimension(width, height));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		addKeyListener(new Keyboard());
		
		gp = new GamePanel();
		add(gp);
		gp.startGameLoop();
	}

}
