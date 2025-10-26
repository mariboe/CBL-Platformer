package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import levels.LevelManager;
import main.entities.Player;

public class Game implements Runnable {
	
	
	private final GameWindow gameWindow;
	private final GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	private Player player;
	private LevelManager levelManager;
	private boolean paused = false;

	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 1.5f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    public final int selectedLevel;

	public Game(int selectedLevel) {
		
		this.selectedLevel = selectedLevel;
		initClasses();

		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();

		startGameLoop();
	}

	private void initClasses() {
		levelManager = new LevelManager(this);
		player = new Player(100, 300, (int) (78 * SCALE), (int) (58 * SCALE));
		player.loadLevelData(levelManager.getCurrentLevel(selectedLevel).getLevelData(selectedLevel), selectedLevel);
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {
		if (!paused) {
			player.update();
		}
	}

	public void render(Graphics g) {
		levelManager.draw(g, selectedLevel);
		player.render(g);

		if (paused) {
			drawPauseMenu(g);
		}
	}

	private void drawPauseMenu(Graphics g) {
        // drawing the pause menu
        g.setColor(new Color(0, 0, 0, 150)); // Semi-transparent overlay
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        String text = "PAUSED";
        int textWidth = g.getFontMetrics().stringWidth(text);
        g.drawString(text, (GAME_WIDTH - textWidth) / 2, GAME_HEIGHT / 2);

        g.setFont(new Font("Arial", Font.PLAIN, 24));
        String resumeText = "Press Esc to Resume and E to exit the game";
        int resumeWidth = g.getFontMetrics().stringWidth(resumeText);
        g.drawString(resumeText, (GAME_WIDTH - resumeWidth) / 2, GAME_HEIGHT / 2 + 50);
    }

	public void pause() {
		paused = !paused;
	}

	public void exit() {
		gameWindow.exitWindow();
	}

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();

		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while (true) {

			if (paused) {
				deltaU = 0;
			}

			long currentTime = System.nanoTime();
			
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				deltaU--;
			}

			if (deltaF >= 1) {
				gamePanel.repaint();
				deltaF--;
			}

			
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
			}
		}

	}

	public void windowFocusLost() {
		player.resetDirectionBooleans();
	}

	public Player getPlayer() {
		return player;
	}
}
