package Events;

import Game.Game;
import Game.PopupWindow;

/**
 * Represents a treasure event that grants the player money.
 */
public class TreasureEvent implements RandomEvent {
    public String getName() {
        return "Treasure Found";
    }

    @Override
    public boolean isPermanent() {
        return false;
    }
    /**
     * Applies the treasure event by giving the player 100 money.
     *
     * @param game the game instance
     */
    @Override
    public void aply(Game game) {
        game.setMoney(game.getMoney() + 100);
        new PopupWindow("Found treasure! +100 money");
    }

    @Override
    public void remove(Game game) {

    }


}