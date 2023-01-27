package com.example.checkers.client.controller.buttons;

import com.example.checkers.client.controller.ActionPerformer;
import com.example.checkers.client.view.GameExistsWindow;
import com.example.checkers.client.view.boards.GameBoard;
import com.example.checkers.client.view.buttons.ConnectButton;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.PrintWriter;

/**
 * Giving buttons function of connecting to game
 */

public abstract class ConnectionToGame {

    private static String name;
    private static String bot;
    private static Stage previousStage;
    private static PrintWriter out;

    protected ConnectionToGame() {

    }

    protected abstract GameBoard chooseGameBoard(String name, String answer, PrintWriter out);

    public static void setConnectionOnClick(ConnectButton button, Stage stage, TextField textField, CheckBox botCheckBox, PrintWriter outputPrinter) {

        button.setOnAction(e -> {

            name = textField.getText();
            bot = botCheckBox.isSelected() ? "yes" : "no";
            previousStage = stage;
            out = outputPrinter;
            ConnectingToGame.createGame(name, button.getName(), bot,  out);
        });
    }

    /**
     * Creating the game and closing start window
     * @param answer Answer from the server about game data
     */

    public void setGame(String answer) {

        Platform.runLater(() -> {

            if (answer.equals("1") || answer.equals("2")) {

                Stage stage = new Stage();
                GameBoard gameBoard = chooseGameBoard(name, answer, out);
                ActionPerformer.setGameBoard(gameBoard);
                Scene scene = new Scene(gameBoard);

                stage.setScene(scene);
                stage.setTitle(name);

                previousStage.close();
                stage.show();

            } else if (answer.equals("no")) {

                GameExistsWindow.display();
            }
        });
    }
}
