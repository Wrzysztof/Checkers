package com.example.checkers.client;

import com.example.checkers.client.view.ClientPane;
import com.example.checkers.client.view.boards.GameBoard;

public final class ActionPerformer {

    private static GameBoard game;

    public static void setGameBoard(GameBoard gameBoard) {

        game = gameBoard;
    }
    public static void check(String command) {

        String[] commands = command.split(" ");

        if (game.getName().equals(commands[0])) {

            //
        }
    }
}
