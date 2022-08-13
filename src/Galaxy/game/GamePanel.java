package Galaxy.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import Galaxy.engine.Game;
import Galaxy.engine.Keyboard;
import Galaxy.game.entities.Player;

public class GamePanel extends JPanel implements Runnable {
	
	public static final double fps = 120.0;
	double updateTime = 1.0 / fps;
	
	GameManager gm;
	
	public GamePanel(GameManager gm) {
		this.gm = gm;
		
		setBackground(Color.black);
	}
	
	public void startGameLoop() {
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		double lastTime = System.nanoTime();
		
		while(true) {
			double currentTime = System.nanoTime();
			double deltaTime = (currentTime - lastTime);
			
			boolean shouldUpdate = (deltaTime >= updateTime*1000000000);
			
			if (shouldUpdate) {
				gm.update(deltaTime);
				repaint();
				lastTime = currentTime;
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		gm.drawGame(g2);
		
		g2.dispose();
	}
	
}
