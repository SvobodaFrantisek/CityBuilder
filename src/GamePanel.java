import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class GamePanel extends JPanel {


    private final int tileSize = 50;
    private BuildingType selectedType = null;
    private boolean destroyMode = false;
    private boolean enabledButtons = true;

    private JLabel population;
    private JButton buttonHouse;
    private JButton buttonFactory;
    private JButton buttonDestroy;
    private JButton buttonShop;
    ArrayList<JButton>buttons = new ArrayList<>();
    private BuildingType factory = new BuildingType("Factory", Color.blue, 1, 0);
    private BuildingType house = new BuildingType("House", Color.red, 3, 4);

    Game game;
    public GamePanel(Game game) {
        this.game = game;

        setLayout(new BorderLayout());


        JPanel textPanel = new JPanel();
        population = new JLabel("Population " + game.getPopulation());
        add(textPanel, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();
        buttonHouse = new JButton("House (" + house.getRemaining() + ")");
        buttonFactory = new JButton("Factory (" + factory.getRemaining() + ")");
        buttonDestroy = new JButton("Destroy");
        buttonShop = new JButton("Shop");

        buttons.add(buttonHouse);
        buttons.add(buttonFactory);
        buttons.add(buttonDestroy);
        buttons.add(buttonShop);





        buttonHouse.setActionCommand("house");
        buttonPanel.add(buttonHouse);

        buttonFactory.setActionCommand("factory");
        buttonPanel.add(buttonFactory);


        buttonDestroy.setActionCommand("destroy");
        buttonPanel.add(buttonDestroy);
        add(buttonPanel, BorderLayout.SOUTH);

        buttonShop.setActionCommand("shop");
        buttonPanel.add(buttonShop);
        add(buttonPanel, BorderLayout.SOUTH);


        add(buttonPanel, BorderLayout.SOUTH);

        updateValues();
        ActionListener listener = e -> {
            String cmd = e.getActionCommand();
            switch (cmd) {
                case "house":
                    selectedType = house;
                    destroyMode = false;
                    break;
                case "factory":
                    selectedType = factory;
                    destroyMode = false;
                    break;
                case "destroy":
                    selectedType = null;
                    destroyMode = true;
                    break;
                    case "shop":
                        enabledButtons = false;
                        turnOnOffButtons();
                        new ShopWindow(game, this);
                        break;
            }
        };

        buttonHouse.addActionListener(listener);
        buttonFactory.addActionListener(listener);
        buttonDestroy.addActionListener(listener);
        buttonShop.addActionListener(listener);



        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / tileSize;
                int y = e.getY() / tileSize;

                if (destroyMode) {
                    if(game.destroyBuilding(x, y)) {
                        updateValues();
                        repaint();
                    }
                }

                if (selectedType != null && selectedType.getRemaining() > 0) {
                    Building b = new Building(selectedType.getName(), selectedType.getColor(), selectedType);
                    if (game.placeBuilding(x, y, b)) {
                        selectedType.decreaseRemaining();
                        game.addPopulation(selectedType.getPopulationBoost());
                        updateValues();
                        repaint();
                    }
                }
            }
        });
        textPanel.add(population);
    }
    public void updateValues(){
        population.setText("Population: " + game.getPopulation());
        buttonHouse.setText("House (" + house.getRemaining() + ")");
        buttonFactory.setText("Factory (" + factory.getRemaining() + ")");
    }
    public void turnOnOffButtons(){
        for (int i = 0; i < buttons.size(); i++) {
            if (!enabledButtons) {
                buttons.get(i).setEnabled(false);
            } else {
                buttons.get(i).setEnabled(true);
            }
        }

    }

    public boolean isEnabledButtons() {
        return enabledButtons;
    }

    public void setEnabledButtons(boolean enabledButtons) {
        this.enabledButtons = enabledButtons;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Tile[][] grid = game.getGrid();

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int x = col * tileSize;
                int y = row * tileSize;


                g.setColor(Color.black);
                g.drawRect(x-1, y-1, tileSize, tileSize);

                Building b = grid[row][col].getBuilding();
                if (b != null) {
                    g.setColor(b.getColor());
                    g.fillRect(x, y, tileSize - 1, tileSize - 1);
                }
            }
        }
    }
}