package com.example.checkers.server;

import com.example.checkers.server.view.ServerPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerApplication extends Application {
    @Override
    public void start(Stage stage) {

        try {

            ServerPane root = new ServerPane(stage);
            Scene scene = new Scene(root);

            stage.setTitle("Warcaby - Server");
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
