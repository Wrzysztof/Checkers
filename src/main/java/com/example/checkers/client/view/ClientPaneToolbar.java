package com.example.checkers.client.view;

import com.example.checkers.client.view.buttons.CanadianCheckersButton;
import com.example.checkers.client.view.buttons.ConnectButton;
import com.example.checkers.client.view.buttons.EnglishCheckersButton;
import com.example.checkers.client.view.buttons.PolishCheckersButton;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ClientPaneToolbar extends HBox {

    public ClientPaneToolbar(Stage stage, TextField textField) {

        final ConnectButton polishConnectButton = new PolishCheckersButton(stage, textField);
        final ConnectButton englishConnectButton = new EnglishCheckersButton(stage, textField);
        final ConnectButton canadianConnectButton = new CanadianCheckersButton(stage, textField);

        setSpacing(40);
        setPadding(new Insets(10));
        getChildren().addAll(polishConnectButton, englishConnectButton, canadianConnectButton);
    }
}
