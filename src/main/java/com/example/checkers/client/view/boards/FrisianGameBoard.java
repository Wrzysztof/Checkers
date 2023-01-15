package com.example.checkers.client.view.boards;

import java.io.PrintWriter;

/**
 * Setting Frisian game board values
 */

public class FrisianGameBoard extends GameBoard {
    public FrisianGameBoard(String gameName, String player, PrintWriter outputPrinter) {
        super(gameName, player, outputPrinter);
    }

    @Override
    protected int setSize() {
        return 10;
    }
}
