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
import java.util.Objects;
import java.util.Scanner;

public class ClientApplication extends Application implements Runnable {

    private Socket socket = null;
    private BufferedReader inputBuffer = null;
    private PrintWriter outputPrinter = null;
    private ClientPane root = null;

    @Override
    public void start(Stage stage) {

        try {

            Thread thread = new Thread(this);
            thread.start();

            root = new ClientPane(stage, inputBuffer, outputPrinter);
            Scene scene = new Scene(root);

            stage.setTitle("Warcaby");
            stage.setScene(scene);
            stage.show();



        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {

            socket = new Socket("127.0.0.1", 1234);
            outputPrinter = new PrintWriter(socket.getOutputStream(), true);
            inputBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner sc = new Scanner(System.in);
            String line = null;

            while(!"exit".equalsIgnoreCase(line)) {

                line = inputBuffer.readLine();

                if (Objects.equals(line.charAt(0), 'r')) {

                    ActionPerformer.check(line);
                }
            }

            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}