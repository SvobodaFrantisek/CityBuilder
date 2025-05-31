package Events;

import Game.Game;
import Game.PopupWindow;

public class FamineEvent implements RandomEvent {
    private boolean applied = false;

    @Override
    public String getName() {
        return "Famine";
    }

    @Override
    public boolean isPermanent() {
        return true;
    }

    @Override
    public void aply(Game game) {
        if (!applied) {
            new PopupWindow("Famine started - slowed production of food");
            applied = true;
        }
        game.setFood(game.getFood()-1);
    }

    @Override
    public void remove(Game game) {
        new PopupWindow("Famine removed");
    }
}
