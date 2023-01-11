package com.example.checkers.server;

import com.example.checkers.server.controller.ActionChecker;
import com.example.checkers.server.view.ServerPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApplication extends Application implements Runnable {
    @Override
    public void start(Stage stage) {

        try {

            ServerPane root = new ServerPane(stage);
            Scene scene = new Scene(root);

            stage.setTitle("Warcaby - Server");
            stage.setScene(scene);
            stage.show();

            Thread thread = new Thread(this);
            thread.start();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        ServerSocket server = null;

        try {

            server = new ServerSocket(1234);
            server.setReuseAddress(true);

            while(true) {

                Socket client = server.accept();
                ClientHandler clientSocket = new ClientHandler(client);
                ActionChecker.addClient(clientSocket);
                new Thread(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class ClientHandler implements Runnable {

        private final Socket clientSocket;
        private PrintWriter out = null;
        private BufferedReader in = null;

        public ClientHandler(Socket socket) {

            this.clientSocket = socket;
        }

        public void run() {

            try {

                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String line;

                while ((line = in.readLine()) != null) {

                    ActionChecker.check(line, out);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void sendMessage(String message) {

            out.println(message);
            out.flush();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
