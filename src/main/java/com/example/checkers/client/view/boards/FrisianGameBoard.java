package com.example.checkers.client.view.boards;

public class FrisianGameBoard extends GameBoard {
    public FrisianGameBoard(String gameName) {
        super(gameName);
    }

    @Override
    protected int setSize() {
        return 10;
    }
}