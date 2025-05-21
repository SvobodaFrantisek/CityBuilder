import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopWindow extends JDialog {
    public ShopWindow(Game game, GamePanel gamePanel) {
        super((Frame) null, "Shop", true);

        setUndecorated(true);
        setSize(400, 400);
        setLocationRelativeTo(null);
        ShopPanel shopPanel = new ShopPanel(game, gamePanel);
        add(shopPanel);
        JButton exitButton = new JButton("Exit");
        JPanel buttonPanel = new JPanel();
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamePanel.setEnabledButtons(true);
                gamePanel.turnOnOffButtons();
                dispose();
            }
        });
        buttonPanel.add(exitButton);
        setLayout(new BorderLayout());
        buttonPanel.setBackground(Color.gray);
        add(buttonPanel, BorderLayout.NORTH);
        add(shopPanel, BorderLayout.CENTER);
        setVisible(true);

    }
}
