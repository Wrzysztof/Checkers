package com.example.checkers.server.model;

public class EnglishGameLogic extends GameLogic {
    public EnglishGameLogic(String name) {
        super(name);
    }

    @Override
    protected int setBoardSize() {
        return 8;
    }
}
