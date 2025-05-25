package Game;
import javax.swing.*;

public class GameWindow {
    public JFrame frame;
    public GameWindow() {
        frame = new JFrame();
        frame.setTitle("City Builder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setResizable(false);
        Game game = new Game();
        GamePanel panel = new GamePanel(game);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
