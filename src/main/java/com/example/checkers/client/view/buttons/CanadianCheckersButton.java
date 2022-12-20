package com.example.checkers.client.view.buttons;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CanadianCheckersButton extends ConnectButton {
    public CanadianCheckersButton(Stage stage, TextField textField) {

        super(stage, textField);
    }

    @Override
    protected String setName() {

        return "Kanadyjskie";
    }
}
