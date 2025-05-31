package Game;

import Events.RandomEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RemoveEventShop extends JFrame {
    private Game game;

    public RemoveEventShop(Game game) {
        this.game = game;
        setTitle("Remove Buff");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panel.setBackground(new Color(240, 240, 255));

        ArrayList<RandomEvent> events = game.getActiveEvents();
        boolean isEvent = false;

        for (int i = 0; i < events.size(); i++) {
            RandomEvent event = events.get(i);

            if (event.isPermanent()) {
                isEvent = true;

                JPanel row = new JPanel();
                row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
                row.setAlignmentX(Component.CENTER_ALIGNMENT);
                row.setBackground(new Color(220, 220, 240));

                JLabel label = new JLabel(event.getName());
                label.setFont(new Font("Arial", Font.BOLD, 14));

                int cost = getRepairCost(event.getName());
                JButton removeButton = new JButton("Remove (" + cost + "$)");
                removeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (game.getMoney() >= cost) {
                            game.setMoney(game.getMoney() - cost);
                            game.removeEvent(event);
                            dispose();
                        } else {
                            new PopupWindow("Not enough money!");
                        }
                    }
                });

                row.add(label);
                row.add(removeButton);
                panel.add(row);

            }
        }

        if (!isEvent) {
            JLabel noEvents = new JLabel("No permanent events to remove.");
            noEvents.setFont(new Font("b", Font.BOLD, 14));
            noEvents.setForeground(Color.DARK_GRAY);
            noEvents.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(noEvents);
        }

        add(panel);
        setVisible(true);
    }

    private int getRepairCost(String eventName) {
        if (eventName.equals("ZombieApocalypse")) {
            return 50;
        }
        if (eventName.equals("ToxicWater")) {
            return 70;
        }
        if (eventName.equals("Famine")) {
            return 80;
        }
        return 100;
    }
}
