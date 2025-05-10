import java.awt.Color;

public class Building {
    private String name;
    private Color color;
    private int remaining;

    public Building(String name, Color color, int remaining) {
        this.name = name;
        this.color = color;
        this.remaining = remaining;
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

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
