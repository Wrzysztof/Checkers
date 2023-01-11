package com.example.checkers.server.model;

public class EnglishGameLogic extends GameLogic {
    public EnglishGameLogic(String name) {
        super(name);
    }

    @Override
    protected int setBoardSize() {
        return 8;
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
