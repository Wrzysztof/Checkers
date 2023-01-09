package com.example.checkers.server.controller;

import com.example.checkers.server.model.ServerGames;

public final class ActionChecker {

    private final ServerGames games = new ServerGames();

    public void check(String command) {

        String[] commands = command.split(" ");

        if (commands[2].equals("1")) {

            if(games.ifGameExists(commands[0])) {


            } else {

                games.newGame(commands[0], commands[3]);
            }
        }

        else if (commands[2].equals("2")) {

            games.getGame(commands[0]).doMove(commands[3], commands[4], commands[5]);
        }
    }
}
