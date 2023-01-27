package com.example.checkers.client.view.buttons;

import com.example.checkers.client.controller.buttons.ConnectionToFrisianGame;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Button to connect to Frisian game
 */

public class FrisianConnectButton extends ConnectButton {
    public FrisianConnectButton(Stage stage, TextField textField, CheckBox botCheckBox, BufferedReader inputBuffer, PrintWriter outputPrinter) {

        super(stage, textField, botCheckBox, inputBuffer, outputPrinter);
    }

    @Override
    protected String setName() {

        return "Fryzyjskie";
    }

    @Override
    protected void connectToGame(Stage stage, TextField textField, CheckBox botCheckBox, BufferedReader inputBuffer, PrintWriter outputPrinter) {

        ConnectionToFrisianGame.setConnectionOnClick(this, stage, textField, botCheckBox, outputPrinter);
    }
}
