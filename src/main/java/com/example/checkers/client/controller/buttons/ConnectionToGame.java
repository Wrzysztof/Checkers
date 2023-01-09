package com.example.checkers.client.controller.buttons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public final class ConnectionToGame {

    public static void createGame(String gameName, String gameType, BufferedReader inputBuffer, PrintWriter outputPrinter) {

        outputPrinter.println(gameName + " " + "player" + " " + "checkgame" + " " + gameType);
        String player = "dupa";

        while (!(player.equals("1") || player.equals("2") || player.equals("3"))) {
            try {

                player = inputBuffer.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        outputPrinter.println(gameName + " " + player + " " + "newgame" + " " + gameType);
    }
}
