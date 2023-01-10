package com.example.checkers.server.model;

import com.example.checkers.client.controller.pawns.MovingPawns;

import java.util.ArrayList;

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

        GameLogic game = null;

        if (type.equals("Polskie")) {

            game = new PolishGameLogic(name);
        } else if (type.equals("Angielskie")) {

            game = new EnglishGameLogic(name);
        } else {

            game = new FrisianGameLogic(name);
        }



        games.add(game);
    }

    public GameLogic getGame(String name) {

        for (GameLogic game : games) {

            if (game.getName().equals(name)) {

                return game;
            }
        }

        return null;
    }
}
