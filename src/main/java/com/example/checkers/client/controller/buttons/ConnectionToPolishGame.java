package com.example.checkers.client.controller.buttons;

import com.example.checkers.client.view.boards.GameBoard;
import com.example.checkers.client.view.boards.PolishGameBoard;
import com.example.checkers.client.view.buttons.ConnectButton;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public final class ConnectionToPolishGame {

    public static void setConnectionOnClick(ConnectButton button, Stage previousStage, TextField textField, BufferedReader inputBuffer, PrintWriter outputPrinter) {

        button.setOnAction(e -> {

            ConnectionToGame.createGame(textField.getText(), button.getName(), inputBuffer, outputPrinter);

            boolean stop = false;

            while (!stop) {

                try {

                    String line = inputBuffer.readLine();

                    if(line.equals("1") || line.equals("2")) {

                        Stage stage = new Stage();
                        GameBoard gameBoard = new PolishGameBoard(button.getName(), line, inputBuffer, outputPrinter);
                        Scene scene = new Scene(gameBoard);

                        stage.setScene(scene);
                        stage.setTitle(textField.getText());

                        previousStage.close();
                        stage.show();

                        stop = true;
                    } else if (line.equals("no")) {

                        stop = true;
                    }

                } catch (IOException ex) {

                }
            }
        });
    }
}
