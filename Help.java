import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Help extends JPanel implements ActionListener{

    private MainFrame mainFrame;

    private JPanel topPanel;
    private JPanel centerPanel;
    private JButton backButton;

    private JLabel helpLabel;

    private Dimension buttonSize;

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

        helpLabel = new JLabel("FIGURE IT OUT YOURSELF");

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
