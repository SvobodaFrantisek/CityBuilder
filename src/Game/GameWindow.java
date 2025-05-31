package Game;

import javax.swing.*;
import java.util.ArrayList;
/**
 * Initializes and displays the main game window using Swing.
 * Sets up the game panel and configures the frame properties.
 */
public class GameWindow {
    public JFrame frame;
    /**
     * Constructs a new GameWindow and displays it.
     */
    public GameWindow() {
        frame = new JFrame();
        frame.setTitle("City Builder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 650);
        Game game = new Game();
        GamePanel gamePanel = new GamePanel(game);
        frame.setResizable(false);
        frame.add(gamePanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
