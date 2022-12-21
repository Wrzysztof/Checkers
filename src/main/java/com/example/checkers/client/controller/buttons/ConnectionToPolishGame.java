package com.example.checkers.client.controller.buttons;

import com.example.checkers.client.model.GameLogic;
import com.example.checkers.client.model.PolishGameLogic;
import com.example.checkers.client.view.boards.GameBoard;
import com.example.checkers.client.view.boards.PolishGameBoard;
import com.example.checkers.client.view.buttons.ConnectButton;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public final class ConnectionToPolishGame {

    public static void setConnectionOnClick(ConnectButton button, Stage previousStage, TextField textField) {

        button.setOnAction(e -> {

            //send info to server about game logic

            Stage stage = new Stage();
            GameBoard gameBoard = new PolishGameBoard(button.getName());
            Scene scene = new Scene(gameBoard);

            stage.setScene(scene);
            stage.setTitle(textField.getText());

            previousStage.close();
            stage.show();
        });
    }
}
