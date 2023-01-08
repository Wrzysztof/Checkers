package com.example.checkers.server.model;

import java.util.ArrayList;

public class Game {

    private final String name;
    private final ArrayList<Pawn> pawns;

    public Game() {

        pawns = null;
        name = null;
    }

    public String getName() {

        return name;
    }

    public void doMove(String x1, String y2, String x, String y) {


    }
}
