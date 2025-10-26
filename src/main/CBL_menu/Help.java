package main.CBL_menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Help extends JPanel implements ActionListener{

    private final MainFrame mainFrame;

    private final JPanel topPanel;
    private final JPanel centerPanel;
    private final JButton backButton;

    private final JLabel helpLabel;

    private final Dimension buttonSize;

    public Help(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new BorderLayout());
        
        // Make top Panel
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Make the button
        buttonSize = new Dimension(200, 100);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setPreferredSize(buttonSize);
        backButton.setFont(new Font("Arial", Font.BOLD, 24));

        topPanel.add(backButton);

        this.add(topPanel, BorderLayout.NORTH);

        // Center button
        centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        helpLabel = new JLabel("FIGURE IT OUT YOURSELF(Read the README.md of controls :) )");

        centerPanel.add(helpLabel);

        this.add(centerPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == backButton) {
            mainFrame.showPanel("MainMenu");
        }
    }
    
}
