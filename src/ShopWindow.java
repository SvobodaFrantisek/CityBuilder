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
        JButton exitButton = new JButton("Exit");
        JPanel buttonPanel = new JPanel();
        JPanel panel = new JPanel();
        JPanel shopPanel = new JPanel();
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamePanel.setEnabledButtons(true);
                gamePanel.turnOnOffButtons();
                dispose();
            }
        });
        panel.setLayout(new BorderLayout());
        shopPanel.setLayout(new GridLayout(2,3,10,10));
        shopPanel.add(ItemPanel(new BuildingType("House", Color.red, 3, 4,0,0,0,0),game,gamePanel));
        shopPanel.add(ItemPanel(new BuildingType("Factory", Color.blue, 1, 0,0,0,0,0),game,gamePanel));
        shopPanel.add(ItemPanel(new BuildingType("House", Color.red, 3, 4,0,0,0,0),game,gamePanel));
        shopPanel.add(ItemPanel(new BuildingType("House", Color.red, 3, 4,0,0,0,0),game,gamePanel));
        shopPanel.add(ItemPanel(new BuildingType("House", Color.red, 3, 4,0,0,0,0),game,gamePanel));
        shopPanel.add(ItemPanel(new BuildingType("House", Color.red, 3, 4,0,0,0,0),game,gamePanel));

        JLabel titleLabel = new JLabel("Shop");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel, BorderLayout.NORTH);
        buttonPanel.add(exitButton);
        setLayout(new BorderLayout());
        panel.setBackground(Color.gray);
        shopPanel.setBackground(Color.gray);
        buttonPanel.setBackground(Color.gray);
        add(panel, BorderLayout.CENTER);
        panel.add(shopPanel);
        add(buttonPanel, BorderLayout.NORTH);
        setVisible(true);
    }
    public JPanel ItemPanel(BuildingType b, Game game, GamePanel gamePanel) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(Color.lightGray);


        JLabel name = new JLabel(b.getName());
        name.setFont(new Font("Tahoma", Font.BOLD, 16));
        JLabel cost = new JLabel("wood " + b.getCostWood());
        JLabel cost1 = new JLabel("stone " + b.getCostStone());
        JLabel cost2 = new JLabel("money " + b.getCostMoney());
        JLabel cost3 = new JLabel("iron " + b.getCostIron());

        JButton buy = new JButton("BUY");
        buy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(game.canBuy(b)){
                    game.buy(b);
                    switch (b.getName()){
                        case "House":
                            gamePanel.getHouse().setRemaining(gamePanel.getHouse().getRemaining() +1);
                            break;
                        case "Factory":
                            gamePanel.getFactory().setRemaining(gamePanel.getFactory().getRemaining() +1);
                            break;
                    }
                    gamePanel.updateValues();
                }
            }
        });

        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        cost.setAlignmentX(Component.CENTER_ALIGNMENT);
        cost1.setAlignmentX(Component.CENTER_ALIGNMENT);
        cost2.setAlignmentX(Component.CENTER_ALIGNMENT);
        cost3.setAlignmentX(Component.CENTER_ALIGNMENT);
        buy.setAlignmentX(Component.CENTER_ALIGNMENT);


        itemPanel.add(name, BorderLayout.CENTER);
        itemPanel.add(cost, BorderLayout.CENTER);
        itemPanel.add(cost1, BorderLayout.CENTER);
        itemPanel.add(cost2, BorderLayout.CENTER);
        itemPanel.add(cost3, BorderLayout.CENTER);
        itemPanel.add(buy, BorderLayout.CENTER);
        return itemPanel;


    }
}
