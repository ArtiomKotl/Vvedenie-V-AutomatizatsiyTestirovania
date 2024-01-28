package org.example.homeWork001;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


public class GameTest {
    Player gamer;
    Game game;
    List<Door> doors;

    @BeforeEach
    void SetUp () {
        doors = new ArrayList<>();
    }

    @Test
    void testGameRoundPlayerChangeChoiceAndLoose () {
        gamer = new Player("Игрок", true);
        doors.add(new Door(true));
        doors.add(new Door(false));
        doors.add(new Door(false));
        game = new Game(gamer, doors);

        boolean check = game.round(0).isPrize();

        Assertions.assertFalse(check);

    }

    @Test
    void testGameRoundPlayerChangeChoiceAndWin () {
        gamer = new Player("Игрок", true);
        doors.add(new Door(false));
        doors.add(new Door(true));
        doors.add(new Door(false));
        game = new Game(gamer, doors);
        boolean check = game.round(0).isPrize();

        Assertions.assertTrue(check);

    }

    @Test
    void testGameRoundPlayerNotChangeChoiceAndLoose () {
        gamer = new Player("Игрок", false);
        doors.add(new Door(true));
        doors.add(new Door(false));
        doors.add(new Door(false));
        game = new Game(gamer, doors);

        boolean check = game.round(0).isPrize();

        Assertions.assertTrue(check);

    }

    @Test
    void testGameRoundPlayerNotChangeChoiceAndWin () {
        gamer = new Player("Игрок", false);
        doors.add(new Door(false));
        doors.add(new Door(true));
        doors.add(new Door(false));
        game = new Game(gamer, doors);
        boolean check = game.round(0).isPrize();

        Assertions.assertFalse(check);

    }





}

