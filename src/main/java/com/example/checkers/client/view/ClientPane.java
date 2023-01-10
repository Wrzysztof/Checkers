package com.example.checkers.client.view;

import com.example.checkers.client.view.boards.GameBoard;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ClientPane extends BorderPane {

    public ClientPane(Stage stage, BufferedReader inputBuffer, PrintWriter outputPrinter) {

        final TextField textField = new TextField("Wpisz nazwę gry");
        final Label label = new Label("Wybierz wariant");
        toolbar = new ClientPaneToolbar(stage, textField, inputBuffer, outputPrinter);
        setTop(textField);
        setCenter(label);
        setBottom(toolbar);

        setMinSize(294, 150);
        setPadding(new Insets(20));
    }
}
