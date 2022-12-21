package com.example.checkers.client.view.boards;

public class PolishGameBoard extends GameBoard {
    public PolishGameBoard(String gameName) {
        super(gameName);
    }

    @Override
    protected int setSize() {

        return 10;
    }
}
