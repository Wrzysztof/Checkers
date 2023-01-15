package com.example.checkers.client.controller.buttons;

import com.example.checkers.client.view.boards.GameBoard;
import com.example.checkers.client.view.boards.PolishGameBoard;

import java.io.PrintWriter;

/**
 * Creating Polish game
 */

public final class ConnectionToPolishGame extends ConnectionToGame {

    @Override
    public GameBoard chooseGameBoard(String name, String answer, PrintWriter out) {
        return new PolishGameBoard(name, answer, out);
    }
}
