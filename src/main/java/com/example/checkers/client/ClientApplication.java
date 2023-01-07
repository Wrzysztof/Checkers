package com.example.checkers.client;

import com.example.checkers.client.view.ClientPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientApplication extends Application {
    @Override
    public void start(Stage stage) {

        try {

            //ClientPane root = new ClientPane(stage);
            //Scene scene = new Scene(root);

            //stage.setTitle("Warcaby");
            //stage.setScene(scene);
            //stage.show();

            try (Socket socket = new Socket("localhost", 1234)) {

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                Scanner sc = new Scanner(System.in);
                String line = null;

                while(!"exit".equalsIgnoreCase(line)) {

                    line = sc.nextLine();

                    out.println(line);
                    out.flush();

                    //do game.something
                }

                sc.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}