package com.example.checkers.server.model;

import java.util.ArrayList;

/**
 * Holding data about games playing on the server
 */

public class ServerGames {

    private static ArrayList<GameLogic> games;

    public ServerGames() {

        games = new ArrayList<>();
    }

    public boolean ifGameExists(String name) {

        for (GameLogic game:games) {

            if (game.getName().equals(name)) {

                return true;
            }
        }

        return false;
    }

    public boolean ifGameStarted(String name) {

        return getGame(name).isStarted();
    }

    public void newGame(String name, String type) {

        GameLogic game;

        if ("Polskie".equals(type)) {

            game = new PolishGameLogic(name);
        } else if ("Angielskie".equals(type)) {

            game = new EnglishGameLogic(name);
        } else {

            game = new FrisianGameLogic(name);
        }

        games.add(game);
    }

    public GameLogic getGame(String name) {

        for (GameLogic game : games) {

            if (name.equals(game.getName())) {

                return game;
            }
        }

        return null;
    }
}
