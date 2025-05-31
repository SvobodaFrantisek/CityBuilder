package Game;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Displays the game tutorial loaded from a text file.
 */
public class TutorialFrame extends JFrame {
    /**
     * Constructs the tutorial frame and loads its content from file.
     */
    public TutorialFrame() {
        setTitle("Game Tutorial");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Serif", Font.PLAIN, 16));


        try (BufferedReader reader = new BufferedReader(new FileReader("src/tutorial.txt"))) {
            StringBuilder tutorialText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                tutorialText.append(line).append("\n");
            }
            textArea.setText(tutorialText.toString());
        } catch (IOException e) {
            textArea.setText("error while loading tutorial");
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}