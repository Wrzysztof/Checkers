package com.example.checkers.client.view.boards;

import com.example.checkers.client.view.boards.elements.Pawn;
import com.example.checkers.client.view.boards.elements.Tile;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class GameBoard extends GridPane {

    private final ArrayList<ArrayList<Tile>> tiles;
    private final HashMap<Integer, Pawn> pawns;
    private final int size;

    public GameBoard(String gameName) {

        super();
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        tiles = new ArrayList<>();
        pawns = new HashMap<>();
        size = setSize();
        drawBoard(size);
    }

    private void drawBoard(int size) {

        int pawnsCounter = 1;

        for (int i = 0; i < size; i++) {

            ArrayList<Tile> column = new ArrayList<>();

            for (int j = 0; j < size; j++) {

                Color color = (j + i) % 2 == 0 ? Color.SANDYBROWN : Color.WHEAT;
                Tile tile = new Tile(this, color);
                //tile.widthProperty().bind(widthProperty().divide(600));
                //tile.heightProperty().bind(heightProperty().divide(600));
                add(tile, i, j);
                GridPane.setHalignment(tile, HPos.CENTER);
                column.add(tile);

                if(((i + j) % 2 == 0 && j < (size / 2 - 1)) || ((i + j) % 2 == 0 && j > (size / 2))) {

                    color = j > (size / 2 - 1) ? Color.WHITE : Color.BLACK;
                    Pawn pawn = new Pawn(this, color);
                    add(pawn, i, j);
                    GridPane.setHalignment(pawn, HPos.CENTER);
                    pawns.put(pawnsCounter, pawn);
                    pawnsCounter++;
                }
            }

            tiles.add(column);
        }
    }

    protected abstract int setSize();

    public int getSize() {

        return size;
    }

    public Tile getTile(int x, int y) {

        return tiles.get(x).get(y);
    }

    public Pawn getPawn(int key) {

        return pawns.get(key);
    }
}
