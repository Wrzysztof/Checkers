package com.example.checkers.client.view;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Main client window displayed on program start
 */

public class ClientPane extends BorderPane {

    public ClientPane(Stage stage, BufferedReader inputBuffer, PrintWriter outputPrinter) {

        final TextField textField = new TextField("Wpisz nazwę gry");
        final Label label = new Label("Wybierz wariant");
        final CheckBox botCheckBox = new CheckBox("Gra z botem");
        final ClientPaneToolbar toolbar = new ClientPaneToolbar(stage, textField, botCheckBox, inputBuffer, outputPrinter);
        final VBox vBox = new VBox(toolbar, botCheckBox);
        setTop(textField);
        setCenter(label);
        setBottom(vBox);

        setMinSize(294, 150);
        setPadding(new Insets(20));
    }
}
