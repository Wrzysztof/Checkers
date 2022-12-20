package com.example.checkers.client.controller.pawns;

import javafx.scene.Node;

public final class DraggableMaker {

    private static double startX;
    private static double startY;
    private static double defaultX;
    private static double defaultY;

    public static void makeDraggable(Node node) {

        node.setOnMousePressed(e -> {

            defaultX = e.getSceneX();
            defaultY = e.getSceneY();
            startX = e.getSceneX() - node.getTranslateX();
            startY = e.getSceneY() - node.getTranslateY();
        });

        node.setOnMouseDragged(e -> {

            node.setTranslateX(e.getSceneX() - startX);
            node.setTranslateY(e.getSceneY() - startY);
        });

        node.setOnMouseReleased(e -> {

            node.setTranslateX(defaultX - startX);
            node.setTranslateY(defaultY - startY);
            node.toFront();
        });
    }
}
