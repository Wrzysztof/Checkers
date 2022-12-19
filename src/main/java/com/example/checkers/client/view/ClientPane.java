package com.example.checkers.client.view;

import com.example.checkers.client.view.boards.GameBoard;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ClientPane extends BorderPane {
    public ClientPane() {

        final Label label = new Label("Wybierz wariant");
        final ClientPaneToolbar toolbar = new ClientPaneToolbar();
        final GameBoard gameBoard = new GameBoard();
        setCenter(gameBoard);
        setTop(label);
        setBottom(toolbar);
    }
}
