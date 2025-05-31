package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * A modal dialog window for purchasing additional buildings.
 */
public class ShopWindow extends JDialog {
    /**
     * Constructs the shop interface for buying building types.
     *
     * @param game reference to the game for resource checking and purchasing
     * @param gamePanel reference to the game panel to update building counts
     */
    public ShopWindow(Game game, GamePanel gamePanel) {
        super((Frame) null, "Shop", true);

        setUndecorated(true);
        setSize(500, 400);
        setLocationRelativeTo(null);
        JButton exitButton = new JButton("Exit");
        JPanel buttonPanel = new JPanel();
        JPanel panel = new JPanel();
        JPanel shopPanel = new JPanel();
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.setLayout(new BorderLayout());
        shopPanel.setLayout(new GridLayout(2, 3, 10, 10));
        shopPanel.add(ItemPanel(new BuildingType("House", Color.red, 0, 4, 2, 5, 20, 5), game, gamePanel));
        shopPanel.add(ItemPanel(new BuildingType("Factory", Color.white, 0, 0, 15, 10, 10, 5), game, gamePanel));
        shopPanel.add(ItemPanel(new BuildingType("Farm", Color.yellow, 0, 0, 2, 5, 10, 10), game, gamePanel));
        shopPanel.add(ItemPanel(new BuildingType("WaterPump", Color.blue, 0, 0, 10, 10, 5, 5), game, gamePanel));
        shopPanel.add(ItemPanel(new BuildingType("Mine", Color.gray, 0, 0, 10, 20, 15, 15), game, gamePanel));
        shopPanel.add(ItemPanel(new BuildingType("LumberMill", Color.green, 0, 0, 5, 10, 10, 10), game, gamePanel));

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
    /**
     * Creates a panel for a shop item including costs and buy button.
     *
     * @param b building type to display
     * @param game game instance for checking resources
     * @param gamePanel for updating counts
     * @return JPanel representing the shop item
     */
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
                if (game.canBuy(b)) {
                    game.buy(b);
                    switch (b.getName()) {
                        case "House":
                            gamePanel.getHouse().setRemaining(gamePanel.getHouse().getRemaining() + 1);
                            break;
                        case "Factory":
                            gamePanel.getFactory().setRemaining(gamePanel.getFactory().getRemaining() + 1);
                            break;
                        case "Farm":
                            gamePanel.getFarm().setRemaining(gamePanel.getFarm().getRemaining() + 1);
                            break;
                        case "WaterPump":
                            gamePanel.getWaterPump().setRemaining(gamePanel.getWaterPump().getRemaining() + 1);
                            break;
                        case "Mine":
                            gamePanel.getMine().setRemaining(gamePanel.getMine().getRemaining() + 1);
                            break;
                        case "LumberMill":
                            gamePanel.getLumberMill().setRemaining(gamePanel.getLumberMill().getRemaining() + 1);
                            break;
                    }
                    gamePanel.updateValues();
                } else {
                    new PopupWindow("not enough resources");
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
