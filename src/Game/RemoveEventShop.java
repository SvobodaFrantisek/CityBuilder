package Game;

import Events.RandomEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RemoveEventShop extends JFrame {
    private Game game;

    public RemoveEventShop(Game game) {
        this.game = game;
        setTitle("Remove buff");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 1));

        ArrayList<RandomEvent> events = game.getActiveEvents();
        for (int i = 0; i < events.size(); i++) {
            RandomEvent event = events.get(i);
            if (!event.isPermanent()) continue;

            JButton removeButton = new JButton("Remove " + event.getName());
           removeButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   int cost = getRepairCost(event.getName());

                   if (game.getMoney() >= cost) {
                       game.setMoney(game.getMoney() - cost);
                       game.removeEvent(event);
                       dispose();
                   } else {
                       new PopupWindow("Not enough money!");
                   }
               }
           });
           add(removeButton);
        }

        setVisible(true);
    }

    private int getRepairCost(String eventName) {
        if (eventName.equals("ZombieApocalypse")){
            return 50;
        }
        if (eventName.equals("")) {
            return 80;
        }
        if (eventName.equals("ToxicWater")) {
            return 70;
        }
        return 100;
    }
}


