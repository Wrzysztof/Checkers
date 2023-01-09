package com.example.checkers.client.view.boards;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class EnglishGameBoard extends GameBoard {
    public EnglishGameBoard(String gameName, String player, BufferedReader inputBuffer, PrintWriter outputPrinter) {
        super(gameName, player, inputBuffer, outputPrinter);
    }

    @Override
    protected int setSize() {
        return 8;
    }
}
