package com.example.checkers.client.view.boards.elements;

import com.example.checkers.client.controller.pawns.DraggingPawns;
import com.example.checkers.client.view.boards.GameBoard;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.PrintWriter;

/**
 * Pawn displayed on board
 */

public class Pawn extends Circle {

    private final GameBoard board;
    private final int key;

    public Pawn(GameBoard board, Color color, int key, PrintWriter outputPrinter) {

        super(30, color);
        this.board = board;
        this.key = key;

        DraggingPawns.makeMovable(this, outputPrinter);
    }

    public GameBoard getBoard() {

        return board;
    }

    public int getKey() {

        return key;
    }

    public void setKing() {

        setStroke(Color.GOLD);
    }
}
