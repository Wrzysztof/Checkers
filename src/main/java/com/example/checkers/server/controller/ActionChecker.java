package com.example.checkers.server.controller;

import com.example.checkers.server.ServerApplication;
import com.example.checkers.server.model.ServerGames;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Receiving information from the clients and sending back messages what to do
 */

public final class ActionChecker {

    private static final Object LOCK = new Object();
    private static final ServerGames GAMES = new ServerGames();
    private static final List<ServerApplication.ClientHandler> CLIENTS = new ArrayList<>();

    public static void addClient(ServerApplication.ClientHandler client) {

        synchronized (LOCK) {

            CLIENTS.add(client);
        }
    }

    private static void distributeMessage(String message) {

        System.out.println(message);

        List<ServerApplication.ClientHandler> clientsCopy;
        synchronized (LOCK) {
            clientsCopy = new ArrayList<>(CLIENTS);
        }
        for (ServerApplication.ClientHandler client : clientsCopy) {
            client.sendMessage(message);
        }
    }

    /**
     * Reading messages from the client and checking what to do
     * @param command The message from the client
     * @param out PrintWriter to which messages are sent
     */

    public static void check(String command, PrintWriter out) {

        String[] commands = command.split(" ");

        if (commands[2].equals("newgame")) {

            if (commands[4].equals("no")) {

                if (GAMES.ifGameExists(commands[0])) {

                    if (commands[1].equals("2") && GAMES.getGame(commands[0]).getType().equals(commands[3])) {

                        GAMES.getGame(commands[0]).startGame();
                        out.println(commands[3] + " 2");
                    } else {

                        out.println(commands[3] + " no");
                    }

                } else {

                    GAMES.newGame(commands[0], commands[3], false);
                    out.println(commands[3] + " 1");
                }
            } else if (commands[4].equals("yes")) {

                if (GAMES.ifGameExists(commands[0])) {

                    out.println(commands[3] + " no");

                } else {

                    GAMES.newGame(commands[0], commands[3], true);
                    GAMES.getGame(commands[0]).startGame();
                    out.println(commands[3] + " 1");
                }
            }

        } else if (commands[2].equals("checkgame")) {

           if (GAMES.ifGameExists(commands[0])) {

               if(GAMES.ifGameStarted(commands[0])) {

                   out.println(commands[0] + " 3 " + commands[3] + " " + commands[4]);
               } else {
                   out.println(commands[0] + " 2 " + commands[3] + " " + commands[4]);
               }
           } else {
               out.println(commands[0] + " 1 " + commands[3] + " " + commands[4]);
           }

        } else if (commands[2].equals("move")) {

            System.out.println(command);
            distributeMessage(GAMES.getGame(commands[0]).doMove(commands[1], commands[3], commands[4], commands[5]));

            if (GAMES.getGame(commands[0]).ifBot()) {

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                distributeMessage(GAMES.getGame(commands[0]).doBotMove());
            }
        }
    }
}
