package main.CBL_menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LevelSelection extends JPanel implements ActionListener{

    private MainFrame mainFrame;

    private JPanel topPanel;
    private JPanel centerPanel;

    private JButton backButton;
    private JButton level1;
    private JButton level2; 
    private JButton level3;
    private JButton level4;

    private Dimension buttonSize;

    private JLabel instruction;

    private Image backgroundImage;

    public LevelSelection(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new BorderLayout());

        // Load background image
        backgroundImage = new ImageIcon("src/main/CBL_menu/res/Clouds.jpg").getImage();

        
        // Make the top panel
        topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());


        // Make the button
        buttonSize = new Dimension(200, 100);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setPreferredSize(buttonSize);
        backButton.setFont(new Font("Arial", Font.BOLD, 24));

        topPanel.add(backButton, BorderLayout.WEST);

        // Make the label and add to panel
        instruction = new JLabel("CHOOSE YOUR LEVEL", SwingConstants.CENTER);
        instruction.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(instruction, BorderLayout.CENTER);


        this.add(topPanel, BorderLayout.NORTH);
        

        // Make the center panel
        centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new GridLayout(2, 2, 40, 40)); 

        buttonSize = new Dimension(150, 100); // rectangular shape
        level1 = new JButton("Level 1");
        level2 = new JButton("Level 2");
        level3 = new JButton("Level 3");
        level4 = new JButton("Level 4");

        JButton[] buttons = {level1, level2, level3, level4};
        for (JButton b : buttons) {
            b.setPreferredSize(buttonSize);
            b.setFont(new Font("Arial", Font.BOLD, 20));
            b.addActionListener(this);
            centerPanel.add(b);
        }

        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); 
        this.add(centerPanel, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw image scaled to fit the panel
    
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == backButton) {
            mainFrame.showPanel("MainMenu");
        } else if (source == level1) {
            SwingUtilities.invokeLater(() -> {
                new main.Game(0);
            });
        } else if (source == level2) {
            SwingUtilities.invokeLater(() -> {
                new main.Game(1);
            });
        } else if (source == level3) {
            SwingUtilities.invokeLater(() -> {
                new main.Game(2);
            });
        } else if (source == level4) {
            SwingUtilities.invokeLater(() -> {
                new main.Game(3);
            });
        }
    }    
}