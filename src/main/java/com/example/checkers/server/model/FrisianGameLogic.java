package com.example.checkers.server.model;

public class FrisianGameLogic extends GameLogic {
    public FrisianGameLogic(String name) {
        super(name);
    }

    @Override
    protected int setBoardSize() {
        return 10;
    }
}
