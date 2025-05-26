package Events;
import Game.Game;
import Game.PopupWindow;


public class TreasureEvent implements RandomEvent {
    public String getName() { return "Treasure Found"; }

    @Override
    public boolean isPermanent() {
        return false;
    }

    @Override
    public void aply(Game game) {
        game.setMoney(game.getMoney() + 100);
        new PopupWindow("Found treasure! +100 money");
    }

    @Override
    public void remove(Game game) {

    }



}