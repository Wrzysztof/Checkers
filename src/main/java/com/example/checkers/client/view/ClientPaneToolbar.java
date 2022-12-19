package com.example.checkers.client.view;

import com.example.checkers.client.view.buttons.ConnectButton;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public class ClientPaneToolbar extends HBox {

    public ClientPaneToolbar() {

        final ConnectButton connectButton = new ConnectButton();

        setSpacing(10);
        setPadding(new Insets(10));
        getChildren().addAll(connectButton);
    }
}
