package com.example.checkers.client.view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ClientPane extends BorderPane {
    public ClientPane() {

        final Label label = new Label("Wybierz wariant");
        final ClientPaneToolbar toolbar = new ClientPaneToolbar();
        setTop(label);
        setBottom(toolbar);
    }
}
