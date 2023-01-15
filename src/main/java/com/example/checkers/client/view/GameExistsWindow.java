package com.example.checkers.client.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class GameExistsWindow {

    public static void display() {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Błąd");
        window.setMinWidth(240);
        window.setMinHeight(160);

        Label label = new Label("Ta nazwa już jest zajęta");
        Button closeButton = new Button("Zamknij");
        closeButton.setOnAction(e -> {

            window.close();
        });

        VBox box = new VBox(10);
        box.getChildren().addAll(label, closeButton);
        box.setAlignment(Pos.CENTER);

        final Scene scene = new Scene(box);

        window.setScene(scene);
        window.showAndWait();
    }
}
