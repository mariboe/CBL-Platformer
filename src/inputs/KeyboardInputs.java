package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;

public class KeyboardInputs implements KeyListener {

	private final GamePanel gamePanel;

	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A -> gamePanel.getGame().getPlayer().setLeft(false);
			case KeyEvent.VK_D -> gamePanel.getGame().getPlayer().setRight(false);
			case KeyEvent.VK_SPACE -> gamePanel.getGame().getPlayer().setJump(false);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A -> gamePanel.getGame().getPlayer().setLeft(true);
			case KeyEvent.VK_D -> gamePanel.getGame().getPlayer().setRight(true);
			case KeyEvent.VK_SPACE -> gamePanel.getGame().getPlayer().setJump(true);
		}
	}
}
