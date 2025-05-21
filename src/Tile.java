public class Tile {
    private int x;
    private int y;
    private Building building;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.building = null;
    }

    public boolean isEmpty() {
        return building == null;
    }

    public void placeBuilding(Building b) {
        this.building = b;
    }
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

