package com.example.checkers.client.view;

import javafx.scene.control.Label;


public final class GameEndWindow {

    public static void display(String result) {

        Label label = new Label("Koniec gry - " + result);
        GameWindow.display(label, "Koniec");
    }
}