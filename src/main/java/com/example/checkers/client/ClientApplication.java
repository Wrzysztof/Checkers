package com.example.checkers.client;

import com.example.checkers.client.view.ClientPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApplication extends Application {
    @Override
    public void start(Stage stage) {

        try {

            ClientPane root = new ClientPane(stage);
            Scene scene = new Scene(root);

            stage.setTitle("Warcaby");
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}