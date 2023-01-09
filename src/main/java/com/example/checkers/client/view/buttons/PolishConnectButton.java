package com.example.checkers.client.view.buttons;

import com.example.checkers.client.controller.buttons.ConnectionToPolishGame;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class PolishConnectButton extends ConnectButton {
    public PolishConnectButton(Stage stage, TextField textField, BufferedReader inputBuffer, PrintWriter outputPrinter) {

        super(stage, textField, inputBuffer, outputPrinter);
    }

    @Override
    protected String setName() {

        return "Polskie";
    }

    @Override
    protected void connectToGame(Stage stage, TextField textField, BufferedReader inputBuffer, PrintWriter outputPrinter) {

        ConnectionToPolishGame.setConnectionOnClick(this, stage, textField, inputBuffer, outputPrinter);
    }
}
