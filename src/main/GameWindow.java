package main;

import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

public class GameWindow {
	
	private final JFrame jframe;

	public GameWindow(GamePanel gamePanel) {

		jframe = new JFrame();

		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.setLocationRelativeTo(null);
		jframe.setLocation(150, 100);
		jframe.setResizable(false);
		jframe.pack();
		jframe.setTitle("FlatPormer");
		jframe.setVisible(true);
		jframe.addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowLostFocus(java.awt.event.WindowEvent e) {
				gamePanel.getGame().windowFocusLost();
			}

			@Override
			public void windowGainedFocus(java.awt.event.WindowEvent e) {
				gamePanel.requestFocus();
			}

		});

	}

	public void exitWindow() {
		jframe.dispose();
	}

}
