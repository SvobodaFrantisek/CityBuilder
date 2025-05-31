package Game;
/**
 * Represents a single tile on the game grid.
 */
public class Tile {
    private int x;
    private int y;
    private Building building;
    /**
     * Constructs a tile with the given coordinates.
     *
     * @param x the horizontal grid index
     * @param y the vertical grid index
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.building = null;
    }

    public boolean isEmpty() {
        return building == null;
    }
    /** Places a building on this tile */
    public void placeBuilding(Building b) {
        this.building = b;
    }
    /** Removes the building from this tile */
    public void destroyBuilding() {
        this.building = null;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}

