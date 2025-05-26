package Events;
import Game.Game;
import Game.PopupWindow;
public class ZombieApocalypse implements RandomEvent{
    private boolean applied = false;

    @Override
    public String getName() {
        return "ZombieApocalypse";
    }

    @Override
    public boolean isPermanent() {
        return true;
    }

    @Override
    public void aply(Game game) {
        if (!applied) {
            new PopupWindow("Zombie apocalypse started, population began to decline");
            applied = true;
        }
        game.setPopulation(game.getPopulation() - 1);
    }

    @Override
    public void remove(Game game) {
        new PopupWindow("Zombie Apocalypse cleared");
    }
}
