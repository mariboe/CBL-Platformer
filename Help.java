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

    public Help(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new BorderLayout());
        
        // Back button
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        backButton = new JButton("Back");
        backButton.addActionListener(this);

        topPanel.add(backButton);

        this.add(topPanel, BorderLayout.NORTH);

        // Center button
        centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        helpLabel = new JLabel("FIGURE IT OUT YOURSELF" );

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
