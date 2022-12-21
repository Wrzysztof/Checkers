package com.example.checkers.client.view.buttons;

import com.example.checkers.client.controller.buttons.ConnectionToEnglishGame;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EnglishConnectButton extends ConnectButton {
    public EnglishConnectButton(Stage stage, TextField textField) {

        super(stage, textField);
    }

    @Override
    protected String setName() {

        return "Angielskie";
    }

    @Override
    protected void connectToGame(Stage stage, TextField textField) {

        ConnectionToEnglishGame.setConnectionOnClick(this, stage, textField);
    }
}
