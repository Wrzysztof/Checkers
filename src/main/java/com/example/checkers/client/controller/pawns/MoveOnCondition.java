package com.example.checkers.client.controller.pawns;

import com.example.checkers.client.view.boards.Pawn;
import javafx.scene.Node;

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

            if (!pawn.isMoveLegal()) {

                pawn.setTranslateX(defaultX - startX);
                pawn.setTranslateY(defaultY - startY);
                pawn.toFront();
            }
        });
    }
}
