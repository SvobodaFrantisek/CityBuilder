public class Game {
    private Tile[][] grid;
    private int gridSize = 10;
    private int population = 0;
    private int hosuses = 0;
    private int factories = 0;
    private int food = 0;
    private int iron = 0;
    private int stone = 0;

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
    public void addPopulation(int population) {
        this.population += population;
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
}

