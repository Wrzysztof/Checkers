package com.example.checkers.client.view.boards.elements;

import com.example.checkers.client.controller.pawns.DraggingPawns;
import com.example.checkers.client.view.boards.GameBoard;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class Pawn extends Circle {

    private final GameBoard board;
    private final int key;

    public Pawn(GameBoard board, Color color, int key, BufferedReader inputBuffer, PrintWriter outputPrinter) {

        super(30, color);
        this.board = board;
        this.key = key;

        DraggingPawns.makeMovable(this, inputBuffer, outputPrinter);
    }

    public GameBoard getBoard() {

        return board;
    }

    public int getKey() {

        return key;
    }
}
