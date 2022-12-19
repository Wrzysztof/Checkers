package com.example.checkers.client.view.boards;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameBoard extends GridPane {

    private final ArrayList<ArrayList<Tile>> tiles;
    private final ArrayList<ArrayList<Pawn>> pawns;
    public GameBoard() {

        super();
        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        tiles = new ArrayList<>();
        pawns = new ArrayList<>();

        for (int i = 0; i < 8; i++) {

            ArrayList<Tile> column = new ArrayList<>();

            for (int j = 0; j < 8; j++) {

                Color color = (j + i) % 2 == 0 ? Color.SANDYBROWN : Color.WHEAT;
                Tile tile = new Tile(this, color);
                //tile.widthProperty().bind(widthProperty().divide(600));
                //tile.heightProperty().bind(heightProperty().divide(600));
                add(tile, i, j);
                column.add(tile);

                if(((i + j) % 2 == 0 && j < 3) || ((i + j) % 2 == 0 && j > 4)) {

                    color = j > 3 ? Color.WHITE : Color.BLACK;
                    Pawn pawn = new Pawn(this, color);
                    add(pawn, i, j);
                }
            }

            tiles.add(column);
        }
    }

    public void changePosition(int a, int b, int c, int d) {


    }
}
