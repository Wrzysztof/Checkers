package com.example.checkers.client.view.buttons;

import com.example.checkers.client.controller.buttons.ConnectionToEnglishGame;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Button to connect to English game
 */

public class EnglishConnectButton extends ConnectButton {
    public EnglishConnectButton(Stage stage, TextField textField, CheckBox botCheckBox, BufferedReader inputBuffer, PrintWriter outputPrinter) {

        super(stage, textField, botCheckBox, inputBuffer, outputPrinter);
    }

    @Override
    protected String setName() {

        return "Angielskie";
    }

    @Override
    protected void connectToGame(Stage stage, TextField textField, CheckBox botCheckBox, BufferedReader inputBuffer, PrintWriter outputPrinter) {

        ConnectionToEnglishGame.setConnectionOnClick(this, stage, textField, botCheckBox, outputPrinter);
    }
}
