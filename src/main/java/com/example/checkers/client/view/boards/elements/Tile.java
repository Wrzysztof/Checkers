package com.example.checkers.client.view.boards.elements;

import com.example.checkers.client.view.boards.GameBoard;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private final GameBoard board;

    public Tile(GameBoard board, Color color) {
        super(70, 70, color);
        this.board = board;
    }

    public void setColorToGreen() {

        setFill(Color.GREEN);
    }
}
