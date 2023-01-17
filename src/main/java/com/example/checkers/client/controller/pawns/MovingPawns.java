package com.example.checkers.client.controller.pawns;

import com.example.checkers.client.view.boards.elements.Pawn;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;

/**
 * Changing position of the pawn on the board
 */

public final class MovingPawns {

    public static void doMove(Pawn pawn, int x, int y) {

        Platform.runLater(() -> {
            GridPane.setColumnIndex(pawn, GridPane.getColumnIndex(pawn.getBoard().getTile(x, y)));
            GridPane.setRowIndex(pawn, GridPane.getRowIndex(pawn.getBoard().getTile(x, y)));
            pawn.toFront();
        });
    }
}
