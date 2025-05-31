package Events;

import Game.Game;

public interface RandomEvent {
    String getName();

    boolean isPermanent();

    void aply(Game game);

    void remove(Game game);
}
