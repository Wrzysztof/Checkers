package com.example.checkers.server.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ServerPane extends BorderPane {

    public ServerPane(Stage stage) {

        Label label = new Label("Serwer działa");
        ServerOffButton serverOffButton = new ServerOffButton(stage);
        setTop(label);
        setCenter(serverOffButton);
        setAlignment(label, Pos.CENTER);
        setAlignment(serverOffButton, Pos.CENTER);

        setMinSize(294, 150);
        setPadding(new Insets(20));
    }
}
