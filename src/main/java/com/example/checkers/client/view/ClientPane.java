package com.example.checkers.client.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClientPane extends BorderPane {
    public ClientPane(Stage stage) {

        final TextField textField = new TextField("Wpisz nazwę gry");
        final Label label = new Label("Wybierz wariant");
        final ClientPaneToolbar toolbar = new ClientPaneToolbar(stage, textField);
        setTop(textField);
        setCenter(label);
        setBottom(toolbar);

        setMinSize(294, 150);
        setPadding(new Insets(20));
    }
}
