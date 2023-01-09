package com.example.checkers.client.controller.pawns;

import com.example.checkers.client.view.boards.elements.Pawn;
import javafx.scene.layout.GridPane;

public final class MovingPawns {

    public static void doMove(Pawn pawn, int x, int y) {

        //int px = GridPane.getColumnIndex(pawn);
        //int py = GridPane.getRowIndex(pawn);

        //if (py - 1 == y || py + 1 == y) {

            GridPane.setColumnIndex(pawn, GridPane.getColumnIndex(pawn.getBoard().getTile(x, y)));
            GridPane.setRowIndex(pawn, GridPane.getRowIndex(pawn.getBoard().getTile(x, y)));
        //}
    }
}
