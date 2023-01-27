package com.example.checkers.client.view.buttons;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Button to connect to game
 */

public abstract class ConnectButton extends Button {

    private final String name;

    public ConnectButton(Stage stage, TextField textField, CheckBox botCheckBox, BufferedReader inputBuffer, PrintWriter outputPrinter) {

        super();
        this.name = setName();
        setText(name);
        connectToGame(stage, textField, botCheckBox, inputBuffer, outputPrinter);
    }

    protected abstract String setName();
    protected abstract void connectToGame(Stage stage, TextField textField, CheckBox botCheckBox, BufferedReader inputBuffer, PrintWriter outputPrinter);

    public String getName() {

        return this.name;
    }
}
