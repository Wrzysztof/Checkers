package com.example.checkers.client.view.boards.elements;

import com.example.checkers.client.view.boards.GameBoard;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Tile of the game board
 */

public class Tile extends Rectangle {

    private final GameBoard board;
    private final static int SIZE = 70;

    public Tile(GameBoard board, Color color) {
        super(SIZE, SIZE, color);
        this.board = board;
    }

    public int getSize() {

        return SIZE;
    }
}
