import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {

    private Game game;
    private final int tileSize = 50;
    private Building building = null;
    private BuildingType selectedType = null;

    public GamePanel() {
        this.game = new Game();
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        JButton buttonHouse = new JButton("House");
        buttonHouse.setActionCommand("house");
        buttonPanel.add(buttonHouse);

        JButton buttonFactory = new JButton("Factory");
        buttonFactory.setActionCommand("factory");
        buttonPanel.add(buttonFactory);

        JButton buttonDestroy = new JButton("Destroy");
        buttonDestroy.setActionCommand("destroy");
        buttonPanel.add(buttonDestroy);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton buttonFarm = new JButton("Farm");
        buttonFarm.setActionCommand("farm");
        buttonPanel.add(buttonFarm);
        add(buttonPanel, BorderLayout.SOUTH);


        add(buttonPanel, BorderLayout.SOUTH);

        JPanel textPanel = new JPanel();
        JLabel population = new JLabel("Population: " + game.getPopulation() );
        add(textPanel, BorderLayout.EAST);

        ActionListener listener = e -> {
            String cmd = e.getActionCommand();
            switch (cmd) {
                case "house":
                    BuildingType house = new BuildingType("House", Color.red,3,4);
                    break;
                    case "factory":
                        BuildingType factory = new BuildingType("Factory", Color.blue,1,0);
                        break;

            }
        };
        

        buttonHouse.addActionListener(listener);
        buttonFactory.addActionListener(listener);



        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / tileSize;
                int y = e.getY() / tileSize;

                try {
                    if (building.getRemaining() <= 0) {
                        System.out.println("error");
                    } else {
                        if (game.placeBuilding(x, y, new Building())) {
                            if (building.getName().equals("House")) {
                                game.setHosuses(game.getHosuses() + 1);
                            }

                            building.setRemaining(building.getRemaining() - 1);
                            population.setText("Population: " + game.getPopulation());
                        } else {
                            System.out.println("error");
                        }
                    }
                }catch (Exception exception){
                    System.out.println("error");
                }




                repaint();
            }
        });
        textPanel.add(population);
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