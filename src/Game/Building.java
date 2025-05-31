package Game;

import java.awt.Color;

/**
 * Represents a building placed on a tile in the game.
 */
public class Building {
    private String name;
    private Color color;
    private BuildingType type;
    /**
     * Constructs a Building with specified name, color and type.
     *
     * @param name the name of the building
     * @param color the color used to represent the building
     * @param type the type of the building (e.g., house, factory)
     */
    public Building(String name, Color color, BuildingType type) {
        this.name = name;
        this.color = color;
        this.type = type;

    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BuildingType getType() {
        return type;
    }

    public void setType(BuildingType type) {
        this.type = type;
    }
}
