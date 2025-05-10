import javax.swing.*;

public class GameWindow {
    public JFrame frame;
    public GameWindow() {
        frame = new JFrame();
        frame.setTitle("City Builder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.setVisible(true);
    }

}
