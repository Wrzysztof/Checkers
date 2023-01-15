package com.example.checkers.client.view;

import javafx.scene.control.Label;

public final class GameExistsWindow {

    public static void display() {

        Label label = new Label("Ta nazwa już jest zajęta");
        GameWindow.display(label, "Błąd");
    }
}
