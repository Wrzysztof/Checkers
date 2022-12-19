package com.example.checkers.client.view.boards;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pawn extends Circle {

    private final GameBoard board;

    public Pawn(GameBoard board, Color color) {

        super(30, color);
        this.board = board;
    }
}
