package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * A simple modal dialog that displays messages to the user.
 */
public class PopupWindow extends JDialog {
    /**
     * Constructs and displays a popup window with the specified message.
     *
     * @param message the message to display in the popup
     */
    public PopupWindow(String message) {
        super((Frame) null, "Info", true);
        setSize(new Dimension(400, 200));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        JTextArea msg = new JTextArea(message);
        msg.setEditable(false);
        msg.setOpaque(false);
        msg.setFocusable(false);
        msg.setFont(new Font("Arial", Font.PLAIN, 18));
        msg.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBackground(new Color(245, 245, 255));
        textPanel.add(msg, BorderLayout.CENTER);


        JButton ok = new JButton("OK");
        ok.setFont(new Font("Arial", Font.BOLD, 14));
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 255));
        buttonPanel.add(ok);

        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
