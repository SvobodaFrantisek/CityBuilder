package Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuPanel extends JPanel {
    private ArrayList<JButton>buttons = new ArrayList<>();
    private JButton playButton;
    private JButton exitButton;
    private JButton tutorialButton;
    private JPanel buttonPanel;
    public MenuPanel() {
        buttonPanel = new JPanel();
        playButton = new JButton("Play");
        exitButton = new JButton("Exit");
        tutorialButton = new JButton("Tutorial");

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        setLayout(new GridBagLayout());
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        tutorialButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(playButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(tutorialButton);
        add(buttonPanel);
    }
}
