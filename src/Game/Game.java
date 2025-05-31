package Game;
import java.util.ArrayList;
import java.util.Random;

import Events.*;

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
    private ArrayList<RandomEvent> activeEvents = new ArrayList<>() ;
    private ArrayList<RandomEvent>allEvents = new ArrayList<>() ;
    private int eventCooldown = 0;
    private Random r = new Random();


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
    public boolean destroyBuilding(int x, int y) {
        if (x >= 0 && y >= 0 && y < grid.length && x < grid.length) {
            Tile tile = grid[y][x];
            if (!tile.isEmpty()) {
                Building b = grid[y][x].getBuilding();
                if(b != null) {
                    population -= b.getType().getPopulationBoost();
                }
                tile.destroyBuilding();
                return true;
            }
        }
        return false;
    }
    public boolean canBuy(BuildingType building) {
        if (wood >= building.getCostWood() && stone >= building.getCostStone() && money >= building.getCostMoney() && iron >= building.getCostIron()) {
            return true;
        }else {

            return false;
        }
    }
    public void buy(BuildingType building) {

        wood -= building.getCostWood();
        stone -= building.getCostStone();
        money -= building.getCostMoney();
        iron -= building.getCostIron();
    }

    public void generateResources() {
        int foodProduced = countBuildingsByName("zatim nic") * 2;
        int waterProduced = countBuildingsByName("zatim nic") * 2;
        int woodProduced = countBuildingsByName("zatim nic") * 1;
        int stoneProduced = countBuildingsByName("zatim nic") * 1;
        int ironProduced = countBuildingsByName("zatim nic") * 1;
        int moneyProduced = countBuildingsByName("Factory") * 2;

        food += moneyProduced;
        food -= countBuildingsByName("House") * 2;
        water += moneyProduced;
        water -= countBuildingsByName("House") * 2;
        wood += woodProduced;
        stone += stoneProduced;
        iron += ironProduced;
        money += moneyProduced;

        food = Math.min(food, 1000);
        water = Math.min(water, 1000);
        wood = Math.min(wood, 1000);
        stone = Math.min(stone, 1000);
        iron = Math.min(iron, 1000);
        money = Math.min(money, 1000);


        food = Math.max(food, 0);
        water = Math.max(water, 0);
        population = Math.max(population, 0);

       /* if (food <= 0 || water <= 0) {
            population = Math.max(0, population - 1);
        }

        */
        if (started && population == 0){
            gameOver = true;
        }
    }

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
    public void startEvent() {
        allEvents.add(new TreasureEvent());
        allEvents.add(new ZombieApocalypse());
        allEvents.add(new ToxicWaterEvent());
        allEvents.add(new FamineEvent());
        allEvents.add(new EarthquakeEvent());

        if (eventCooldown > 0){
            eventCooldown--;
            return;
        }
        if (r.nextInt(100) < 20) {

                RandomEvent event = allEvents.get(r.nextInt(allEvents.size()));

                if (event.isPermanent()){
                    activeEvents.add(event);
                }
            event.aply(this);
                eventCooldown = 5;

        }
    }
    public void removeEvent(RandomEvent event) {
        event.remove(this);
        activeEvents.remove(event);
    }

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

