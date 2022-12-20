package com.example.checkers.client.controller;

import com.example.checkers.client.view.boards.GameBoard;
import com.example.checkers.client.view.buttons.ConnectButton;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public final class ConnectionToGame {

    public static void setConnectionOnClick(ConnectButton button, Stage previousStage, TextField textField) {

        button.setOnAction(e -> {

            Stage stage = new Stage();
            GameBoard gameBoard = new GameBoard(button.getName());
            Scene scene = new Scene(gameBoard);

            stage.setScene(scene);
            stage. setTitle(textField.getText());

            previousStage.close();
            stage.show();
        });
    }
}
