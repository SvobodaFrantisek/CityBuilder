import java.awt.Color;

public class Building {
    private String name;
    private Color color;
    private BuildingType type;

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
