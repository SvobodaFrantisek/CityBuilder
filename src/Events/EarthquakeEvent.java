package Events;

import Game.Building;
import Game.Game;
import Game.PopupWindow;
import Game.Tile;

import java.util.ArrayList;
import java.util.Random;
/**
 * Represents a random earthquake event that destroys a few buildings.
 */
public class EarthquakeEvent implements RandomEvent {

    @Override
    public String getName() {
        return "Earthquake";
    }

    @Override
    public boolean isPermanent() {
        return false;
    }
    /**
     * https://chatgpt.com fixed my mistakes but it is nothing i would not have written myself. I did it to safe time.
     *
     * Applies the earthquake effect: destroys up to 3 random buildings and reduces population accordingly.
     *
     * @param game the game instance to apply changes to
     *
     */
    @Override
    public void aply(Game game) {
        ArrayList<Tile> buildings = new ArrayList<>();
        Tile[][] grid = game.getGrid();



        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                Tile t = grid[row][col];
                if (!t.isEmpty()) {
                    buildings.add(t);
                }
            }
        }

        if (buildings.isEmpty()) {
            new PopupWindow("earthquake happened but there is nothing to destroy");
            return;
        }

        Random r = new Random();
        int toDestroy = 3;
        if (buildings.size() < toDestroy) {
            toDestroy = buildings.size();
        }

        for (int i = 0; i < toDestroy; i++) {
            int index = r.nextInt(buildings.size());
            Tile tile = buildings.get(index);

            Building b = tile.getBuilding();
            if (b != null) {
                game.setPopulation(game.getPopulation() - b.getType().getPopulationBoost() / 2);
            }

            tile.destroyBuilding();
            buildings.remove(index);
        }

        new PopupWindow("Earthquake destroyed " + toDestroy + " buildings");
    }

    @Override
    public void remove(Game game) {
    }
}

