package com.example.checkers.client.controller.buttons;

import com.example.checkers.client.view.boards.FrisianGameBoard;
import com.example.checkers.client.view.boards.GameBoard;
import com.example.checkers.client.view.buttons.ConnectButton;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public final class ConnectionToFrisianGame  {

    public static void setConnectionOnClick(ConnectButton button, Stage previousStage, TextField textField) {

        button.setOnAction(e -> {

            Stage stage = new Stage();
            GameBoard gameBoard = new FrisianGameBoard(button.getName());
            Scene scene = new Scene(gameBoard);

            stage.setScene(scene);
            stage. setTitle(textField.getText());

            previousStage.close();
            stage.show();
        });
    }
}
