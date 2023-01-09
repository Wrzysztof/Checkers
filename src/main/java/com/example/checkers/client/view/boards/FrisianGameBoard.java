package com.example.checkers.client.view.boards;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class FrisianGameBoard extends GameBoard {
    public FrisianGameBoard(String gameName, String player, BufferedReader inputBuffer, PrintWriter outputPrinter) {
        super(gameName, player, inputBuffer, outputPrinter);
    }

    @Override
    protected int setSize() {
        return 10;
    }
}
