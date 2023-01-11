package com.example.checkers.client.controller.buttons;

import com.example.checkers.client.controller.ActionPerformer;
import com.example.checkers.client.view.boards.GameBoard;
import com.example.checkers.client.view.boards.PolishGameBoard;
import com.example.checkers.client.view.buttons.ConnectButton;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.PrintWriter;

public final class ConnectionToPolishGame {

    private static String name;
    private static Stage previousStage;
    private static PrintWriter out;

    public static void setConnectionOnClick(ConnectButton button, Stage pStage, TextField textField, PrintWriter outputPrinter) {

        button.setOnAction(e -> {

            name = textField.getText();
            previousStage = pStage;
            out = outputPrinter;
            ConnectionToGame.createGame(name, button.getName(), out);
        });
    }

    public static void setGame(String answer) {

        Platform.runLater(() -> {

            if(answer.equals("1") || answer.equals("2")) {

                Stage stage = new Stage();
                GameBoard gameBoard = new PolishGameBoard(name, answer, out);
                ActionPerformer.setGameBoard(gameBoard);
                Scene scene = new Scene(gameBoard);

                stage.setScene(scene);
                stage.setTitle(name);

                previousStage.close();
                stage.show();

            } else if (answer.equals("no")) {

                //pop up window ze gra istnieje
            }
        });


    }
}
