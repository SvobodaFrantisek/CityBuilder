package Game;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TextPanel extends JPanel {
    private Game game;
    private JLabel population;
    private JLabel money;
    private JLabel wood;
    private JLabel stone;
    private JLabel iron;
    private JLabel food;
    private JLabel water;

    private JLabel picture;
    private ImageIcon happy = new ImageIcon("src/images/happy2.png");
    private ImageIcon sad = new ImageIcon("src/images/happy.png");

    public TextPanel(Game game) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 255, 240));

        this.game = game;
        population = new JLabel("Population: " + game.getPopulation());
        money = new JLabel("Money: " + game.getMoney());
        wood = new JLabel("Wood: " + game.getWood());
        stone = new JLabel("Stone: " + game.getStone());
        iron = new JLabel("Iron: " + game.getIron());
        food = new JLabel("Food: " + game.getFood());
        water = new JLabel("Water: " + game.getWater());

        Font idk = new Font("Arial", Font.BOLD, 25);
        setBorder(new EmptyBorder(5, 30, 5, 30));
        population.setFont(idk);
        money.setFont(idk);
        wood.setFont(idk);
        stone.setFont(idk);
        iron.setFont(idk);
        food.setFont(idk);
        water.setFont(idk);

        wood.setAlignmentX(Component.CENTER_ALIGNMENT);
        population.setAlignmentX(Component.CENTER_ALIGNMENT);
        money.setAlignmentX(Component.CENTER_ALIGNMENT);
        stone.setAlignmentX(Component.CENTER_ALIGNMENT);
        iron.setAlignmentX(Component.CENTER_ALIGNMENT);
        food.setAlignmentX(Component.CENTER_ALIGNMENT);
        water.setAlignmentX(Component.CENTER_ALIGNMENT);



        picture = new JLabel();
        picture.setIcon(happy);
        picture.setAlignmentX(Component.CENTER_ALIGNMENT);





        add(population);
        add(money);
        add(wood);
        add(stone);
        add(iron);
        add(food);
        add(water);
        add(Box.createVerticalStrut(50));
        add(picture);

        updateVal();
    }
    public void updateVal(){
        population.setText("Population: " + game.getPopulation());
        money.setText("Money: " + game.getMoney());
        wood.setText("Wood: " + game.getWood());
        stone.setText("Stone: " + game.getStone());
        iron.setText("Iron: " + game.getIron());
        food.setText("Food: " + game.getFood());
        water.setText("Water: " + game.getWater());
        if (game.getFood() <= 5){
            food.setForeground(Color.RED);
            picture.setIcon(happy);
        }else {
            food.setForeground(Color.BLACK);
            picture.setIcon(sad);
        }
        if (game.getWater() <= 5) {
            picture.setIcon(happy);
            water.setForeground(Color.RED);
        }else {
            water.setForeground(Color.BLACK);
            picture.setIcon(sad);
        }
    }
}
