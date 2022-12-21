package com.example.checkers.client.view.buttons;

import com.example.checkers.client.controller.buttons.ConnectionToPolishGame;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PolishConnectButton extends ConnectButton {
    public PolishConnectButton(Stage stage, TextField textField) {

        super(stage, textField);
    }

    @Override
    protected String setName() {

        return "Polskie";
    }

    @Override
    protected void connectToGame(Stage stage, TextField textField) {

        ConnectionToPolishGame.setConnectionOnClick(this, stage, textField);
    }
}
