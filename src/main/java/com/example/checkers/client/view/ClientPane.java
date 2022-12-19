package com.example.checkers.client.view;

import javafx.scene.layout.BorderPane;

public class ClientPane extends BorderPane {
    public ClientPane() {

        final GameBoard gameBoard = new GameBoard();
        final ClientPaneToolbar toolbar = new ClientPaneToolbar();
        getChildren().addAll(gameBoard, toolbar);
    }
}
