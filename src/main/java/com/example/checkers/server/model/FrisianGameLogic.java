package com.example.checkers.server.model;

public class FrisianGameLogic extends GameLogic {
    public FrisianGameLogic(String name) {
        super(name);
    }

    @Override
    protected int setBoardSize() {
        return 10;
    }

    @Override
    protected String moveWhite(PawnData pawn, int x, int y) {
        return null;
    }

    @Override
    protected String moveBlack(PawnData pawn, int x, int y) {
        return null;
    }
}
