package com.example.checkers.client.view;

import com.example.checkers.client.view.buttons.FrisianConnectButton;
import com.example.checkers.client.view.buttons.ConnectButton;
import com.example.checkers.client.view.buttons.EnglishConnectButton;
import com.example.checkers.client.view.buttons.PolishConnectButton;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Toolbar with buttons for connecting to game
 */

public class ClientPaneToolbar extends HBox {

    public ClientPaneToolbar(Stage stage, TextField textField, CheckBox botCheckBox, BufferedReader inputBuffer, PrintWriter outputPrinter) {

        final ConnectButton polishConnectButton = new PolishConnectButton(stage, textField, botCheckBox, inputBuffer, outputPrinter);
        final ConnectButton englishConnectButton = new EnglishConnectButton(stage, textField, botCheckBox, inputBuffer, outputPrinter);
        final ConnectButton canadianConnectButton = new FrisianConnectButton(stage, textField, botCheckBox, inputBuffer, outputPrinter);

        setSpacing(40);
        setPadding(new Insets(10));
        getChildren().addAll(polishConnectButton, englishConnectButton, canadianConnectButton);
    }
}
