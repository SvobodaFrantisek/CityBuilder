import javax.swing.*;

public class ShopWindow {
    public ShopWindow(Game game){
        JFrame shopFrame = new JFrame("Shop");
        shopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shopFrame.setSize(400, 400);
        shopFrame.setVisible(true);
        ShopPanel shopPanel = new ShopPanel(game);
    }
}
