package Game;

import javax.swing.*;

public class MenuWindow extends JFrame {
    private JFrame frame;

    public MenuWindow() {
        frame = new JFrame();
        frame.setTitle("City Builder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setResizable(false);
        Game game = new Game();
        MenuPanel panel = new MenuPanel();
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
