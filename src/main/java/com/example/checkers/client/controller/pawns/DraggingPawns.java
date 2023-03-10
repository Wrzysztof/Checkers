package com.example.checkers.client.controller.pawns;

import com.example.checkers.client.view.boards.elements.Pawn;

import java.io.PrintWriter;

/**
 * Making pawns able to be dragged and sending move coordinates
 */

public final class DraggingPawns {

    private static double startX;
    private static double startY;
    private static double defaultX;
    private static double defaultY;

    public static void makeMovable(Pawn pawn, PrintWriter outputPrinter) {

        pawn.setOnMousePressed(e -> {

            defaultX = e.getSceneX();
            defaultY = e.getSceneY();
            startX = e.getSceneX() - pawn.getTranslateX();
            startY = e.getSceneY() - pawn.getTranslateY();
            pawn.toFront();
        });

        pawn.setOnMouseDragged(e -> {

            pawn.setTranslateX(e.getSceneX() - startX);
            pawn.setTranslateY(e.getSceneY() - startY);
        });

        pawn.setOnMouseReleased(e -> {

            pawn.setTranslateX(defaultX - startX);
            pawn.setTranslateY(defaultY - startY);

            int size = pawn.getBoard().getSize();
            int tileSize = pawn.getBoard().getTile(1,1).getSize();

            int x = size - 1 - (int)(size - e.getSceneX() / tileSize);
            int y = size - 1 - (int)(size - e.getSceneY() / tileSize);

            outputPrinter.println(pawn.getBoard().getName() + " " + pawn.getBoard().getPlayer() + " move " + pawn.getKey() + " " + x + " " + y);
        });
    }
}
