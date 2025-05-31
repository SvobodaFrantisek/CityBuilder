package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * Displays the main menu for the game with options to play, exit, or view a tutorial.
 */
public class MenuWindow extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private ArrayList<JButton> buttons = new ArrayList<>();
    private JButton playButton;
    private JButton exitButton;
    private JButton tutorialButton;
    private JPanel buttonPanel;
    /**
     * Constructs and displays the menu window.
     */
    public MenuWindow() {
        frame = new JFrame();
        panel = new JPanel();
        frame.setTitle("City Builder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setResizable(false);
        buttonPanel = new JPanel();
        playButton = new JButton("Play");
        exitButton = new JButton("Exit");
        tutorialButton = new JButton("Tutorial");

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        setLayout(new GridBagLayout());
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        tutorialButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameWindow();
                frame.dispose();
            }
        });
        tutorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TutorialFrame();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(playButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(tutorialButton);
        panel.add(buttonPanel);


        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
