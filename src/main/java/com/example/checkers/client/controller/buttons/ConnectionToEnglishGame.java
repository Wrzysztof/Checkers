package com.example.checkers.client.controller.buttons;

import com.example.checkers.client.view.boards.EnglishGameBoard;
import com.example.checkers.client.view.boards.GameBoard;

import java.io.PrintWriter;

/**
 * Creating English game
 */

public final class ConnectionToEnglishGame extends ConnectionToGame {

    @Override
    protected GameBoard chooseGameBoard(String name, String answer, PrintWriter out) {
        return new EnglishGameBoard(name, answer, out);
    }
}
