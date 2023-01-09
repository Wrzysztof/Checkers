package com.example.checkers.server.model;

import com.example.checkers.server.model.GameLogic;

public class PolishGameLogic extends GameLogic {

    public PolishGameLogic(String name) {
        super(name);
    }

    @Override
    protected int setBoardSize() {
        return 10;
    }
}
