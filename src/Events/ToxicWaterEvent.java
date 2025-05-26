package Events;

import Game.Game;
import Game.PopupWindow;

public class ToxicWaterEvent implements RandomEvent {
    private boolean applied = false;

    @Override
    public String getName() {
        return "ToxicWater";
    }

    @Override
    public boolean isPermanent() {
        return true;
    }

    @Override
    public void aply(Game game) {
        if (!applied) {
            new PopupWindow("Toxic water slowed production of water");
            applied = true;
        }
        game.setWater(game.getWater()-1);
    }

    @Override
    public void remove(Game game) {
        new PopupWindow("Toxic water removed");
    }
}
