package Events;

import Game.Building;
import Game.Game;
import Game.PopupWindow;
import Game.Tile;

import java.util.ArrayList;
import java.util.Random;

public class EarthquakeEvent implements RandomEvent {

    @Override
    public String getName() {
        return "Earthquake";
    }

    @Override
    public boolean isPermanent() {
        return false;
    }

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
            new PopupWindow("There is nothing to destroy");
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


            tile.destroyBuilding();
            buildings.remove(index);
        }

        new PopupWindow("Earthquake destroyed " + toDestroy + " buildings");
    }

    @Override
    public void remove(Game game) {
    }
}

