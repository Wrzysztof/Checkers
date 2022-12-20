package com.example.checkers.client.view.buttons;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PolishCheckersButton extends ConnectButton {
    public PolishCheckersButton(Stage stage, TextField textField) {

        super(stage, textField);
    }

    @Override
    protected String setName() {

        return "Polskie";
    }
}
