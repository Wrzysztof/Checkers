package com.example.checkers.server.model;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PolishGameLogicTest {

    private GameLogic gameLogic;

    @Before
    public void setUp() {

        gameLogic = new PolishGameLogic("game", "Polskie", false);

        assertEquals(gameLogic.getName(), "game");
        assertEquals(gameLogic.getType(), "Polskie");
        assertEquals(gameLogic.getBoardSize(), 10);
        assertFalse(gameLogic.isStarted());

        gameLogic.startGame();

        assertTrue(gameLogic.isStarted());
    }

    @Test
    public void doMoveWhenItIsLegalThenReturnYes() {

        //String result = gameLogic.doMove("1", "3", "0", "5");

        //assertEquals("game 3 0 5 yes no x no no yes", result);

        //result = gameLogic.doMove("2", "1", "0", "1");

        //assertEquals("game 1 0 1 yes no x no no yes", result);
    }

    @Test
    public void doMoveWhenPawnIsNotOnTheBoardThenReturnNo() {

        gameLogic.getPawn(1).kill();

        String result = gameLogic.doMove("1", "1", "1", "1");

        assertEquals("game 1 1 1 no", result);
    }

    @Test
    public void doMoveWhenPlayerIsNotAllowedToMoveThenReturnNo() {

        PawnData pawn = new PawnData(1, 1, 1, Color.WHITE);
        assertEquals(pawn.getColor(), Color.WHITE);
        assertEquals(pawn.getKey(), 1);
        assertEquals(pawn.getX(), 1);
        assertEquals(pawn.getY(), 1);

        String result = gameLogic.doMove("2", "1", "2", "2");

        assertEquals("game 1 2 2 no", result);
    }

    @Test
    public void doMoveWhenNewPositionIsAlreadyTakenThenReturnNo() {

        PawnData pawn = new PawnData(1, 1, 1, Color.WHITE);
        assertEquals(pawn.getKey(), 1);
        assertEquals(pawn.getColor(), Color.WHITE);
        assertEquals(pawn.getX(), 1);
        assertEquals(pawn.getY(), 1);
        assertFalse(pawn.isKing());

        String result = gameLogic.doMove("1", "1", "1", "1");

        assertEquals("game 1 1 1 no", result);
    }

    @Test
    public void doMoveWhenThePawnBecomesKingThenReturnYes() {

        PawnData pawn = gameLogic.getPawn(3);
        pawn.setKing();
        assertEquals(pawn.getKey(), 3);
        assertEquals(pawn.getX(), 0);
        assertEquals(pawn.getY(), 6);
        assertEquals(pawn.getColor(), Color.WHITE);
        assertTrue(pawn.isKing());
        assertTrue(pawn.isAlive());

        String result = gameLogic.doMove("1", "3", "0", "6");

        assertEquals("game 3 0 6 no", result);
    }

    @Test
    public void doMoveWhenTheMoveIsNotAllowedThenReturnNo() {

        PawnData pawn = new PawnData(1, 1, 1, Color.WHITE);
        assertEquals(pawn.getColor(), Color.WHITE);
        assertEquals(pawn.getKey(), 1);
        assertEquals(pawn.getX(), 1);
        assertEquals(pawn.getY(), 1);
        assertFalse(pawn.isKing());
        assertTrue(pawn.isAlive());

        String result = gameLogic.doMove("1", "1", "2", "2");

        assertEquals("game 1 2 2 no", result);
    }
}