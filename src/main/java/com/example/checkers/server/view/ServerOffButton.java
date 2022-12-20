package com.example.checkers.server.view;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ServerOffButton extends Button {

    public ServerOffButton(Stage stage) {

        super("Wyłącz");
        setOnAction(e -> {

            stage.close();
        });
    }
}
