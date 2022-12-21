package com.example.checkers.client.controller.pawns;

import com.example.checkers.client.view.boards.Pawn;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public final class MoveOnCondition {

    private static double startX;
    private static double startY;
    private static double defaultX;
    private static double defaultY;

    public static void makeMovable(Pawn pawn) {

        pawn.setOnMousePressed(e -> {

            defaultX = e.getSceneX();
            defaultY = e.getSceneY();
            startX = e.getSceneX() - pawn.getTranslateX();
            startY = e.getSceneY() - pawn.getTranslateY();
        });

        pawn.setOnMouseDragged(e -> {

            pawn.setTranslateX(e.getSceneX() - startX);
            pawn.setTranslateY(e.getSceneY() - startY);
        });

        pawn.setOnMouseReleased(e -> {

            pawn.setTranslateX(defaultX - startX);
            pawn.setTranslateY(defaultY - startY);

            if (!true) {

                int x = 7 - (int)((560 - e.getSceneX()) / 70);
                int y = 7 - (int)((560 - e.getSceneY()) / 70);
                GridPane.setColumnIndex(pawn, GridPane.getColumnIndex(pawn.getBoard().getTile(x, y)));
                GridPane.setRowIndex(pawn, GridPane.getRowIndex(pawn.getBoard().getTile(x, y)));
            }

            pawn.toFront();
        });
    }
}
