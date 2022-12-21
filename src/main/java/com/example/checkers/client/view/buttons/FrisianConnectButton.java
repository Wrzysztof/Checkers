package com.example.checkers.client.view.buttons;

import com.example.checkers.client.controller.buttons.ConnectionToFrisianGame;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FrisianConnectButton extends ConnectButton {
    public FrisianConnectButton(Stage stage, TextField textField) {

        super(stage, textField);
    }

    @Override
    protected String setName() {

        return "Fryzyjskie";
    }

    @Override
    protected void connectToGame(Stage stage, TextField textField) {

        ConnectionToFrisianGame.setConnectionOnClick(this, stage, textField);
    }
}
