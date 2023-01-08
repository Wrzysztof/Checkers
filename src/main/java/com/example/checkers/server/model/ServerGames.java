package com.example.checkers.server.model;

import com.example.checkers.client.controller.pawns.MovingPawns;

import java.util.ArrayList;

public class ServerGames {

    private static ArrayList<Game> games;

    public ServerGames() {

        games = new ArrayList<>();
    }

    public boolean ifGameExists(String name) {

        for (Game game:games) {

            if (game.getName().equals(name)) {

                return true;
            }
        }

        return false;
    }

    public void newGame(String name) {

        Game game = new Game();
        games.add(game);
    }

    public Game getGame(String name) {

        for (Game game : games) {

            if (game.getName().equals(name)) {

                return game;
            }
        }

        return null;
    }
}
