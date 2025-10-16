import java.awt.*;
import javax.swing.*;


public class MainFrame extends JFrame {

    private CardLayout cardLayout;

    private JPanel mainPanel;

    // all the panels
    private MainMenu mainMenu;
    private Help help;
    private Settings settings;
    private LevelSelection levelSelection;

    public MainFrame() {
        // give the frame a name
        super("Platformer Game");

        // Make a new cardLayout and use the cardLayout in de mainPanel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Make the panels
        mainMenu = new MainMenu(this);
        help = new Help(this);
        settings = new Settings(this);
        levelSelection = new LevelSelection(this);


        // Add the Panels to the mainPanel and link them with a special name
        mainPanel.add(mainMenu, "MainMenu");
        mainPanel.add(help, "Help");
        mainPanel.add(settings, "Settings");
        mainPanel.add(levelSelection, "LevelSelection");

        // add the mainPanel to the frame
        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // Make a method that can be used to show a different panel
    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

