package com.example.checkers.client.view;

import com.example.checkers.client.view.buttons.FrisianConnectButton;
import com.example.checkers.client.view.buttons.ConnectButton;
import com.example.checkers.client.view.buttons.EnglishConnectButton;
import com.example.checkers.client.view.buttons.PolishConnectButton;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ClientPaneToolbar extends HBox {

    public ClientPaneToolbar(Stage stage, TextField textField) {

        final ConnectButton polishConnectButton = new PolishConnectButton(stage, textField);
        final ConnectButton englishConnectButton = new EnglishConnectButton(stage, textField);
        final ConnectButton canadianConnectButton = new FrisianConnectButton(stage, textField);

        setSpacing(40);
        setPadding(new Insets(10));
        getChildren().addAll(polishConnectButton, englishConnectButton, canadianConnectButton);
    }
}
