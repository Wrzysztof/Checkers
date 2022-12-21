package com.example.checkers.client.view.buttons;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public abstract class ConnectButton extends Button {

    private final String name;

    public ConnectButton(Stage stage, TextField textField) {

        super();
        this.name = setName();
        setText(name);
        connectToGame(stage, textField);
    }

    protected abstract String setName();
    protected abstract void connectToGame(Stage stage, TextField textField);

    public String getName() {

        return this.name;
    }
}
