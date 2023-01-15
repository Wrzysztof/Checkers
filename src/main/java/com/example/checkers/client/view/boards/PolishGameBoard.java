package com.example.checkers.client.view.boards;

import java.io.PrintWriter;

/**
 * Setting Polish game board values
 */

public class PolishGameBoard extends GameBoard {
    public PolishGameBoard(String gameName, String player, PrintWriter outputPrinter) {
        super(gameName, player, outputPrinter);
    }

    @Override
    protected int setSize() {

        return 10;
    }
}
