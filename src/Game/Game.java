package Game;

import java.util.ArrayList;
import java.util.Random;

import Events.*;
/**
 * The Game class represents the main logic of a city-building game.
 * It manages a grid of tiles, player resources, buildings, population,
 * and random events affecting the game state.
 */
public class Game {
    private Tile[][] grid;
    private int gridSize = 10;
    private int population = 0;
    private int hosuses = 0;
    private int factories = 0;
    private int food = 0;
    private int water = 0;
    private int iron = 0;
    private int stone = 0;
    private int money = 0;
    private int wood = 0;
    private boolean started = false;
    private boolean gameOver = false;
    private ArrayList<RandomEvent> activeEvents = new ArrayList<>();
    private ArrayList<RandomEvent> allEvents = new ArrayList<>();
    private Random r = new Random();

    /**
     * Constructs a new Game and initializes a grid with empty tiles.
     */
    public Game() {
        grid = new Tile[gridSize][gridSize];
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                grid[row][col] = new Tile(col, row);
            }
        }
    }

    public Tile[][] getGrid() {
        return grid;
    }
    /**
     * https://chatgpt.com assisted in this method
     *
     * Attempts to place a building at the specified grid coordinates.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param building the building to place
     * @return true if placed successfully, false otherwise
     */
    public boolean placeBuilding(int x, int y, Building building) {
        if (x >= 0 && y >= 0 && y < grid.length && x < grid.length) {
            Tile tile = grid[y][x];
            if (tile.isEmpty()) {
                tile.placeBuilding(building);
                return true;
            }
        }
        return false;
    }
    /**
     * https://chatgpt.com assisted in this method
     * Attempts to destroy a building at the specified coordinates.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true if a building was destroyed, false otherwise
     */
    public boolean destroyBuilding(int x, int y) {
        if (x >= 0 && y >= 0 && y < grid.length && x < grid.length) {
            Tile tile = grid[y][x];
            if (!tile.isEmpty()) {
                Building b = grid[y][x].getBuilding();
                if (b != null) {
                    population -= b.getType().getPopulationBoost() / 2;
                }
                tile.destroyBuilding();
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if the player can afford to buy a building based on current resources.
     *
     * @param building the building type to check
     * @return true if the player has enough resources, false otherwise
     */
    public boolean canBuy(BuildingType building) {
        if (wood >= building.getCostWood() && stone >= building.getCostStone() && money >= building.getCostMoney() && iron >= building.getCostIron()) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Deducts the cost of a building from the player's resources.
     *
     * @param building the building being purchased
     */
    public void buy(BuildingType building) {
        wood -= building.getCostWood();
        stone -= building.getCostStone();
        money -= building.getCostMoney();
        iron -= building.getCostIron();
    }
    /**
     * Generates resources based on current buildings and adjusts population if needed.
     */
    public void generateResources() {
        int foodProduced = countBuildingsByName("Farm") * 2;
        int waterProduced = countBuildingsByName("WaterPump") * 2;
        int woodProduced = countBuildingsByName("LumberMill") * 1;
        int stoneProduced = countBuildingsByName("Mine") * 1;
        int ironProduced = countBuildingsByName("Mine") * 1;
        int moneyProduced = countBuildingsByName("Factory") * 2;


        food += foodProduced;
        food -= countBuildingsByName("House") * 2;
        water += waterProduced;
        water -= countBuildingsByName("House") * 2;
        wood += woodProduced;
        stone += stoneProduced;
        iron += ironProduced;
        money += moneyProduced;

        food = limitValues(food, 0, 1000);
        water = limitValues(water, 0, 1000);
        wood = limitValues(wood, 0, 1000);
        stone = limitValues(stone, 0, 1000);
        iron = limitValues(iron, 0, 1000);
        money = limitValues(money, 0, 1000);
        population = limitValues(population, 0, 1000);

        if (food <= 0 || water <= 0) {
            population --;
        population = limitValues(population,0,1000);

        }

        if (started && population == 0) {
            gameOver = true;
        }
    }
    /**
     * Limits a resource value to within a specified range.
     *
     * @param value the current value
     * @param min the minimum allowed
     * @param max the maximum allowed
     * @return the clamped value
     */
    private int limitValues(int value, int min, int max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }
    /**
     * Counts the number of buildings of a specific type on the grid.
     *
     * @param name the name of the building
     * @return the number of such buildings
     */
    public int countBuildingsByName(String name) {
        int count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                Building b = grid[row][col].getBuilding();
                if (!(b == null) && b.getName().equals(name)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Randomly triggers a random event if cooldown allows.
     */
    public void startEvent() {
        allEvents.add(new TreasureEvent());
        allEvents.add(new ZombieApocalypse());
        allEvents.add(new ToxicWaterEvent());
        allEvents.add(new FamineEvent());
        allEvents.add(new EarthquakeEvent());

        if (r.nextInt(150) < 3) {

            RandomEvent event = allEvents.get(r.nextInt(allEvents.size()));

            if (event.isPermanent()) {
                activeEvents.add(event);
            }
            event.aply(this);

        }
    }
    /**
     * Removes a specified active event and its effects.
     *
     * @param event the event to remove
     */
    public void removeEvent(RandomEvent event) {
        event.remove(this);
        activeEvents.remove(event);
    }
    /**
     * Returns whether the game is over.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    public ArrayList<RandomEvent> getActiveEvents() {
        return activeEvents;
    }


    public void setGrid(Tile[][] grid) {
        this.grid = grid;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }


    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
    /**
     * Adds population and marks the game as started if population > 0.
     *
     * @param amount amount to add
     */
    public void addPopulation(int amount) {
        population += amount;
        if (population > 0) {
            started = true;
        }
    }


    public int getHosuses() {
        return hosuses;
    }

    public void setHosuses(int hosuses) {
        this.hosuses = hosuses;
    }

    public int getFactories() {
        return factories;
    }

    public void setFactories(int factories) {
        this.factories = factories;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getIron() {
        return iron;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public int getStone() {
        return stone;
    }

    public void setStone(int stone) {
        this.stone = stone;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public void setActiveEvents(ArrayList<RandomEvent> activeEvents) {
        this.activeEvents = activeEvents;
    }
}

