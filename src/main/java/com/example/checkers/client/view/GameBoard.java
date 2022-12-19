package com.example.checkers.client.view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameBoard extends GridPane {

    //private final ArrayList<ArrayList<Tile>> tiles;
    public GameBoard() {

        super();
        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        //tiles = new ArrayList<ArrayList<Tile>>();

        for (int i = 0; i < 8; i++) {

            //ArrayList<Tile> column = new ArrayList<Tile>();

            for (int j = 0; j < 8; j++) {

                Color color = (j + i) % 2 == 0 ? Color.SANDYBROWN : Color.WHEAT;
                Tile cell = new Tile(this, color);
                //cell.widthProperty().bind(widthProperty().divide(600));
                //cell.heightProperty().bind(heightProperty().divide(600));
                add(cell, i, j);
                //column.add(cell);
            }

            //tiles.add(column);
        }
    }
}
