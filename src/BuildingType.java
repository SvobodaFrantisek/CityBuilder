import java.awt.*;

public class BuildingType {
    private String name;
    private Color color;
    private int remaining;
    private int populationBoost;

    public BuildingType(String name, Color color, int remaining, int populationBoost) {
        this.name = name;
        this.color = color;
        this.remaining = remaining;
        this.populationBoost = populationBoost;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getRemaining() {
        return remaining;
    }

    public void decreaseRemaining() {
        if (remaining > 0) remaining--;
    }

    public int getPopulationBoost() {
        return populationBoost;
    }

    public boolean canBuild() {
        return remaining > 0;
    }
}