package Game;
import java.awt.*;

public class BuildingType {
    private String name;
    private Color color;
    private int remaining;
    private int populationBoost;
    private int costIron = 0;
    private int costStone = 0;
    private int costMoney = 0;
    private int costWood = 0;

    public BuildingType(String name, Color color, int remaining, int populationBoost, int costIron, int costStone, int costMoney, int costWood) {
        this.name = name;
        this.color = color;
        this.remaining = remaining;
        this.populationBoost = populationBoost;
        this.costIron = costIron;
        this.costStone = costStone;
        this.costMoney = costMoney;
        this.costWood = costWood;
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

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public void decreaseRemaining() {
        if (remaining > 0) {
            remaining--;
        }
    }


    public int getPopulationBoost() {
        return populationBoost;
    }

    public int getCostIron() {
        return costIron;
    }

    public int getCostStone() {
        return costStone;
    }

    public int getCostMoney() {
        return costMoney;
    }

    public int getCostWood() {
        return costWood;
    }


    public boolean canBuild() {
        return remaining > 0;
    }
}