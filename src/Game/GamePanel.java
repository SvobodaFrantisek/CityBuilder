package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Main game panel responsible for rendering the grid and handling user interaction.
 * Contains buttons to place buildings, destroy them, open shop and remove events.
 */
public class GamePanel extends JPanel {


    private final int tileSize = 50;
    private BuildingType selectedType = null;
    private boolean destroyMode = false;


    private TextPanel textPanel;
    private JButton buttonHouse;
    private JButton buttonFactory;
    private JButton buttonFarm;
    private JButton buttonMine;
    private JButton buttonWater;
    private JButton buttonWood;
    private JButton buttonDestroy;
    private JButton buttonShop;
    private JButton buttonRemoveEvent;
    ArrayList<JButton> buttons = new ArrayList<>();
    private BuildingType factory = new BuildingType("Factory", Color.white, 2, 0, 0, 0, 0, 0);
    private BuildingType house = new BuildingType("House", Color.red, 2, 4, 0, 0, 0, 0);
    private BuildingType farm = new BuildingType("Farm", Color.yellow, 2, 0, 0, 0, 0, 0);
    private BuildingType waterPump = new BuildingType("WaterPump", Color.blue, 2, 0, 0, 0, 0, 0);
    private BuildingType mine = new BuildingType("Mine", Color.gray, 2, 0, 0, 0, 0, 0);
    private BuildingType lumberMill = new BuildingType("LumberMill", Color.GREEN, 2, 0, 0, 0, 0, 0);
    Game game;
    /**
     * Constructs the game panel, sets up buttons, listeners and starts the main game loop.
     *
     * @param game instance of the running game
     */
    public GamePanel(Game game) {
        this.game = game;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 255, 240));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 3, 0, 0));
        buttonHouse = new JButton("House (" + house.getRemaining() + ")");
        buttonFactory = new JButton("Factory (" + factory.getRemaining() + ")");
        buttonFarm = new JButton("Farm (" + farm.getRemaining() + ")");
        buttonMine = new JButton("Mine (" + mine.getRemaining() + ")");
        buttonWater = new JButton("Water Pump (" + waterPump.getRemaining() + ")");
        buttonWood = new JButton("Lumber Mill (" + lumberMill.getRemaining() + ")");

        buttonDestroy = new JButton("Destroy");
        buttonShop = new JButton("Shop");
        buttonRemoveEvent = new JButton("Remove Event");


        textPanel = new TextPanel(game);
        add(textPanel, BorderLayout.EAST);

        buttons.add(buttonHouse);
        buttons.add(buttonFactory);
        buttons.add(buttonDestroy);
        buttons.add(buttonShop);
        buttons.add(buttonRemoveEvent);
        buttons.add(buttonWater);
        buttons.add(buttonWood);
        buttons.add(buttonMine);
        buttons.add(buttonFarm);


        buttonHouse.setActionCommand("house");
        buttonPanel.add(buttonHouse);

        buttonFactory.setActionCommand("factory");
        buttonPanel.add(buttonFactory);

        buttonWater.setActionCommand("water");
        buttonPanel.add(buttonWater);

        buttonWood.setActionCommand("lumberMill");
        buttonPanel.add(buttonWood);

        buttonMine.setActionCommand("mine");
        buttonPanel.add(buttonMine);

        buttonFarm.setActionCommand("farm");
        buttonPanel.add(buttonFarm);


        buttonDestroy.setActionCommand("destroy");
        buttonPanel.add(buttonDestroy);
        add(buttonPanel, BorderLayout.SOUTH);


        buttonShop.setActionCommand("shop");
        buttonPanel.add(buttonShop);


        buttonRemoveEvent.setActionCommand("removeEvent");
        buttonPanel.add(buttonRemoveEvent);
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
                case "water":
                    selectedType = waterPump;
                    destroyMode = false;
                    break;
                case "lumberMill":
                    selectedType = lumberMill;
                    destroyMode = false;
                    break;
                case "mine":
                    selectedType = mine;
                    destroyMode = false;
                    break;
                case "farm":
                    selectedType = farm;
                    destroyMode = false;
                    break;
                case "destroy":
                    selectedType = null;
                    destroyMode = true;
                    break;
                case "shop":
                    new ShopWindow(game, this);
                    break;
                case "removeEvent":
                    new RemoveEventShop(game);
                    break;
            }
        };

        buttonHouse.addActionListener(listener);
        buttonFactory.addActionListener(listener);
        buttonWater.addActionListener(listener);
        buttonWood.addActionListener(listener);
        buttonMine.addActionListener(listener);
        buttonFarm.addActionListener(listener);
        buttonDestroy.addActionListener(listener);
        buttonShop.addActionListener(listener);
        buttonRemoveEvent.addActionListener(listener);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / tileSize;
                int y = e.getY() / tileSize;

                if (destroyMode) {
                    if (game.destroyBuilding(x, y)) {
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

        Timer time = new Timer(1000, e -> {
            game.generateResources();
            updateValues();
            game.startEvent();
            repaint();
            for (int i = 0; i < game.getActiveEvents().size(); i++) {
                game.getActiveEvents().get(i).aply(game);
            }
            if (game.isGameOver()) {
                new PopupWindow("population died. game over");
                System.exit(0);
            }
        });
        time.start();
    }
    /**
     * Updates button labels and resource display.
     */
    public void updateValues() {
        textPanel.updateVal();
        buttonHouse.setText("House (" + house.getRemaining() + ")");
        buttonFactory.setText("Factory (" + factory.getRemaining() + ")");
        buttonFarm.setText("Farm (" + farm.getRemaining() + ")");
        buttonMine.setText("Mine (" + mine.getRemaining() + ")");
        buttonWater.setText("WaterPump (" + waterPump.getRemaining() + ")");
        buttonWood.setText("LumberMill (" + lumberMill.getRemaining() + ")");

    }

    /**
     * https://chatgpt.com fixed my code here and helped me with visual side - round rectangle
     *
     * Custom rendering of the tile grid and buildings.
     *
     * @param g the graphics context
     *
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Tile[][] grid = game.getGrid();
        int rows = grid.length;
        int cols = grid[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * tileSize;
                int y = row * tileSize;
                Graphics2D g2d = (Graphics2D) g;


                g2d.setColor(new Color(235, 255, 235));
                g2d.fillRect(x, y, tileSize, tileSize);

                g2d.setColor(new Color(160, 160, 160));
                g2d.drawRect(x, y, tileSize, tileSize);


                Building b = grid[row][col].getBuilding();
                if (b != null) {
                    g.setColor(b.getColor());
                    g2d.fillRoundRect(x + tileSize / 8, y + tileSize / 8, tileSize - 12, tileSize - 12, 10, 10);
                }
            }
        }
    }

    public BuildingType getFactory() {
        return factory;
    }

    public BuildingType getHouse() {
        return house;
    }

    public BuildingType getLumberMill() {
        return lumberMill;
    }

    public BuildingType getMine() {
        return mine;
    }

    public BuildingType getWaterPump() {
        return waterPump;
    }

    public BuildingType getFarm() {
        return farm;
    }
}