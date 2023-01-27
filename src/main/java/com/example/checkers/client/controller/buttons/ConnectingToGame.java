package com.example.checkers.client.controller.buttons;

import java.io.PrintWriter;

/**
 * Sending messages to server about game creation
 */

public final class ConnectingToGame {

    /**
     * Checking if game exists
     * @param gameName name of the game
     * @param gameType type of the game
     * @param outputPrinter PrintWriter to which messages are sent
     */

    public static void createGame(String gameName, String gameType, String bot, PrintWriter outputPrinter) {

        outputPrinter.println(gameName + " " + "player" + " " + "checkgame" + " " + gameType + " " + bot);
    }

    /**
     * Creating game after check
     * @param gameName name of the game
     * @param gameType type of the game
     * @param player number of player connected to this game
     * @param outputPrinter PrintWriter to which messages are sent
     */

    public static void createRealGame(String gameName, String gameType, String player, String bot, PrintWriter outputPrinter) {

        outputPrinter.println(gameName + " " + player + " " + "newgame" + " " + gameType + " " + bot);
    }
}
