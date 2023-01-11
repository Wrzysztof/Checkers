package com.example.checkers.client.controller.buttons;

import java.io.PrintWriter;

public final class ConnectionToGame {

    public static void createGame(String gameName, String gameType, PrintWriter outputPrinter) {

        outputPrinter.println(gameName + " " + "player" + " " + "checkgame" + " " + gameType);
    }

    public static void createRealGame(String gameName, String gameType, String player, PrintWriter outputPrinter) {

        outputPrinter.println(gameName + " " + player + " " + "newgame" + " " + gameType);
    }
}
