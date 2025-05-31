import Game.BuildingType;
import Game.Game;
import Events.TreasureEvent;
import Events.RandomEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
/**
 * https://chatgpt.com helped here
 * Contains unit tests for verifying core game logic, such as population growth, resource checks, and event effects.
 */
public class UnitTests {

    private Game game;

    @BeforeEach
    public void setup() {
        game = new Game();
    }

    @Test
    public void gameAddPopulationTest() {
        game.addPopulation(5);
        assertEquals(5, game.getPopulation());
    }

    @Test
    public void enoughMoneyTest() {
        BuildingType b = new BuildingType("House", Color.RED, 1, 2, 0, 0,0 , 0); game.setMoney(10);
        assertTrue(game.canBuy(b));
    }

    @Test
    public void notEnoughMoneyTest() {
        BuildingType b = new BuildingType("House", Color.RED, 1, 2, 0, 0, 10, 0);
        game.setMoney(5);
        assertFalse(game.canBuy(b));
    }

    @Test
    public void buyBuldingCorectlyTest() {
        BuildingType b = new BuildingType("House", Color.RED, 1, 2, 0, 0, 10, 0);
        game.setMoney(20);
        game.buy(b);
        assertEquals(10, game.getMoney());
    }

    @Test
    public void startEventTest() {
        int before = game.getMoney();
        RandomEvent e = new TreasureEvent();
        e.aply(game);
        assertTrue(game.getMoney() > before);
    }
}
