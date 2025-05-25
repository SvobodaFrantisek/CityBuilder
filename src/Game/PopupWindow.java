package Game;
import javax.swing.*;
import java.awt.*;

public class PopupWindow extends JDialog {

    public PopupWindow(String message) {
        super((Frame) null ,"info", true );
        setSize(new Dimension(350,150));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel msg = new JLabel(message, SwingConstants.CENTER);
        msg.setFont(new Font("Arial", Font.PLAIN, 20));
        add(msg, BorderLayout.CENTER);
        setVisible(true);

    }

}
