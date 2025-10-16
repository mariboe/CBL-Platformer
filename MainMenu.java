import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class MainMenu extends JPanel implements ActionListener {
    
    private JButton playButton;
    private JButton settingsButton;
    private JButton helpButton;
    private JButton exitButton;

    private JLabel titleLabel;

    private Dimension buttonSize;

    private MainFrame mainFrame;

    public MainMenu(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        
        // Set the layout for this panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Make the title
        titleLabel = new JLabel("PLATFORMER GAME");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalStrut(40));
        this.add(titleLabel);
        this.add(Box.createVerticalStrut(30));

        // Make the buttons and set Max size for buttons
        buttonSize = new Dimension(200, 50);
        playButton = new JButton("Play");
        settingsButton = new JButton("Settings");
        helpButton = new JButton("Help");
        exitButton = new JButton("Exit");
        

        // add everything with list
        JButton[] buttons = { playButton, settingsButton, helpButton, exitButton};
        for (JButton b : buttons) {
            b.setAlignmentX(Component.CENTER_ALIGNMENT); // center horizontally
            b.setPreferredSize(buttonSize);
            b.setMinimumSize(buttonSize);
            b.setMaximumSize(buttonSize); // fix width, prevent stretching
            b.addActionListener(this);
            this.add(b);
            this.add(Box.createVerticalStrut(20));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == playButton) {
            mainFrame.showPanel("LevelSelection");
        } else if (source == settingsButton) {
            mainFrame.showPanel("Settings");
        } else if (source == helpButton) {
            mainFrame.showPanel("Help");
        } else if (source == exitButton) {
            System.exit(0);
        }
    }   
}