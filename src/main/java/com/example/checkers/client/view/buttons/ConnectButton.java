package com.example.checkers.client.view.buttons;

import com.example.checkers.client.controller.buttons.ConnectionToGame;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public abstract class ConnectButton extends Button {

    private final String name;

    public ConnectButton(Stage stage, TextField textField) {

        super();
        this.name = setName();
        setText(name);
        ConnectionToGame.setConnectionOnClick(this, stage, textField);
    }

    protected abstract String setName();

    public String getName() {

        return this.name;
    }
}
