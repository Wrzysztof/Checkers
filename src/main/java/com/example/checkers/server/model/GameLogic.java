package com.example.checkers.server.model;

import javafx.scene.paint.Color;

import java.util.HashMap;

public abstract class GameLogic {

    private final String name;
    private final int boardSize;
    private final HashMap<Integer, PawnData> pawns;

    public GameLogic(String name) {

        this.name = name;
        boardSize = setBoardSize();
        pawns = new HashMap<>();
        int pawnsCounter = 1;

        for (int i = 0; i < boardSize; i++) {

            for (int j = 0; j < boardSize; j++) {

                if(((i + j) % 2 == 0 && j < (boardSize / 2 - 1)) || ((i + j) % 2 == 0 && j > (boardSize / 2))) {

                    Color color = j > (boardSize / 2 - 1) ? Color.WHITE : Color.BLACK;
                    PawnData pawn = new PawnData(i, j, color);
                    pawns.put(pawnsCounter, pawn);
                    pawnsCounter++;
                }
            }
        }
    }

    protected abstract int setBoardSize();

    public String getName() {

        return name;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public PawnData getPawn(int key) {
        return pawns.get(key);
    }

    public void doMove(String number, String newX, String newY) {

        int key = 0;
        int x = 0;
        int y = 0;

        try {

            key = Integer.parseInt(number);
            x = Integer.parseInt(newX);
            y = Integer.parseInt(newY);

        } catch (NumberFormatException e) {

            e.printStackTrace();
        }

        pawns.get(key).setX(x);
        pawns.get(key).setX(y);
    }
}

