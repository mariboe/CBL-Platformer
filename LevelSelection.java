import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.Border;

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

    public LevelSelection(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new BorderLayout());
        
        // Back button
        topPanel = new JPanel();
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

        // Center Panel
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

    
        centerPanel.add(Box.createVerticalStrut(30));

        // Make the buttons and set Max size for buttons
        centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 100));

        buttonSize = new Dimension(90, 90);
        level1 = new JButton("Level 1");
        level2 = new JButton("Level 2");
        level3 = new JButton("Level 3");
        level4 = new JButton("Level 4");

        JButton[] buttons = {level1, level2, level3, level4};
        for (JButton b : buttons) {
            b.setPreferredSize(buttonSize);
            b.addActionListener(this);
            centerPanel.add(b);
        }   

        this.add(centerPanel, BorderLayout.CENTER);
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == backButton) {
            mainFrame.showPanel("MainMenu");
        } else if (source == level1) {
            System.out.println("Level 1");
        } else if (source == level2) {
            System.out.println("Level 2");
        } else if (source == level3) {
            System.out.println("Level 3");
        } else if (source == level4) {
            System.out.println("Level 4");
        }
    }
    
}