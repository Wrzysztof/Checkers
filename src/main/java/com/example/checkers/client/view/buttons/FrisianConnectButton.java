package com.example.checkers.client.view.buttons;

import com.example.checkers.client.controller.buttons.ConnectionToFrisianGame;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class FrisianConnectButton extends ConnectButton {
    public FrisianConnectButton(Stage stage, TextField textField, BufferedReader inputBuffer, PrintWriter outputPrinter) {

        super(stage, textField, inputBuffer, outputPrinter);
    }

    @Override
    protected String setName() {

        return "Fryzyjskie";
    }

    @Override
    protected void connectToGame(Stage stage, TextField textField, BufferedReader inputBuffer, PrintWriter outputPrinter) {

        ConnectionToFrisianGame.setConnectionOnClick(this, stage, textField, inputBuffer, outputPrinter);
    }
}
