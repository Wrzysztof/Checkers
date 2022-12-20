package com.example.checkers.client.view.buttons;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EnglishCheckersButton extends ConnectButton {
    public EnglishCheckersButton(Stage stage, TextField textField) {

        super(stage, textField);
    }

    @Override
    protected String setName() {

        return "Angielskie";
    }
}
