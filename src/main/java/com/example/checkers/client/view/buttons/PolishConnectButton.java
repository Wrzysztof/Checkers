package com.example.checkers.client.view.buttons;

import com.example.checkers.client.controller.buttons.ConnectionToPolishGame;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Button to connect to Polish game
 */

public class PolishConnectButton extends ConnectButton {
    public PolishConnectButton(Stage stage, TextField textField, CheckBox botCheckBox, BufferedReader inputBuffer, PrintWriter outputPrinter) {

        super(stage, textField, botCheckBox, inputBuffer, outputPrinter);
    }

    @Override
    protected String setName() {

        return "Polskie";
    }

    @Override
    protected void connectToGame(Stage stage, TextField textField, CheckBox botCheckBox, BufferedReader inputBuffer, PrintWriter outputPrinter) {

        ConnectionToPolishGame.setConnectionOnClick(this, stage, textField, botCheckBox, outputPrinter);
    }
}
