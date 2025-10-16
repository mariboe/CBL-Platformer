package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private Random random = new Random();
	private MouseInputs mouseInputs;
	private float xDelta = 100, yDelta = 100;
	private float xDirection = 1, yDirection = 1;
	private int frames = 0;
	private long lastCheck = 0;
	private Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));

	public GamePanel() {
		random = new Random();
		mouseInputs = new MouseInputs(this);
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}

	private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	public void changeXDelta(int value) {
		this.xDelta += value;
		repaint();
	}

	public void changeYDelta(int value) {
		this.yDelta += value;
		repaint();
	}
	
	public void setRectPos(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		updateRectangle();
		g.setColor(color);
		g.fillRect((int)xDelta, (int)yDelta, 200, 50);
		File file = new File("./res/player_sprites.png");

	}

	private void updateRectangle() {
		xDelta += xDirection;
		if (xDelta + 200 >= getWidth() || xDelta <= 0) {
			xDirection *= -1;
			color = getRandomColor();
		}

		yDelta += yDirection;
		if (yDelta + 50 >= getHeight() || yDelta <= 0) {
			yDirection *= -1;
			color = getRandomColor();
		}
	}

	private Color getRandomColor() {
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		return new Color(r, g, b);
	}
}
