package com.example.checkers.client.controller.buttons;

import com.example.checkers.client.view.boards.FrisianGameBoard;
import com.example.checkers.client.view.boards.GameBoard;

import java.io.PrintWriter;

/**
 * Creating Frisian Game
 */

public final class ConnectionToFrisianGame extends ConnectionToGame {

    @Override
    protected GameBoard chooseGameBoard(String name, String answer, PrintWriter out) {
        return new FrisianGameBoard(name, answer, out);
    }
}
