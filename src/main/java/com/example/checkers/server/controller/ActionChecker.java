package com.example.checkers.server.controller;

import com.example.checkers.server.model.ServerGames;

import java.io.PrintWriter;

public final class ActionChecker {

    private static final ServerGames games = new ServerGames();

    public static void check(String command, PrintWriter out) {

        String[] commands = command.split(" ");

        if (commands[2].equals("newgame")) {

            if(games.ifGameExists(commands[0])) {

                if(commands[1].equals("2")) {

                    games.getGame(commands[0]).startGame();
                    out.println(commands[3] + " 2");
                } else {

                    out.println(commands[3] + " no");
                }

            } else {

                games.newGame(commands[0], commands[3]);
                out.println(commands[3] + " 1");
            }

        } else if (commands[2].equals("checkgame")) {

           if (games.ifGameExists(commands[0])) {

               if(games.ifGameStarted(commands[0])) {

                   out.println(commands[0] + " 3 " + commands[3]);
               } else {
                   out.println(commands[0] + " 2 " + commands[3]);
               }
           } else {
               out.println(commands[0] + " 1 " + commands[3]);
           }

        } else if (commands[2].equals("move")) {

            out.println(games.getGame(commands[0]).doMove(commands[1], commands[3], commands[4], commands[5]));
        }
    }
}
