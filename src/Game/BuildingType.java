package Game;

import java.awt.*;
/**
 * Represents the blueprint of a building, including costs and limits.
 */
public class BuildingType {
    private String name;
    private Color color;
    private int remaining;
    private int populationBoost;
    private int costIron = 0;
    private int costStone = 0;
    private int costMoney = 0;
    private int costWood = 0;
    /**
     * Constructs a new building type with cost and stats.
     *
     * @param name building name
     * @param color building color
     * @param remaining how many can still be built
     * @param populationBoost how much population this adds
     * @param costIron cost in iron
     * @param costStone cost in stone
     * @param costMoney cost in money
     * @param costWood cost in wood
     */
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
    /** Decreases the remaining count by 1 if above 0 */
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

    /** @return whether this building can still be built */
    public boolean canBuild() {
        return remaining > 0;
    }
}