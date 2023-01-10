package com.example.checkers.client;

import com.example.checkers.client.controller.pawns.MovingPawns;
import com.example.checkers.client.view.boards.GameBoard;

public final class ActionPerformer {

    private static GameBoard game;

    public static void setGameBoard(GameBoard gameBoard) {

        game = gameBoard;
    }
    public static void check(String command) {

        String[] commands = command.split(" ");

        //tak wyglada komenda
        //r name movex movey ifmove ifkill killx killy playerchange ifking ifwin

        if (game.getName().equals(commands[1])) {

            if (commands[4].equals("yes")) {

                //wykonac ruch pawna z com[2] com[3]

                if (commands[5].equals("yes")) {

                    //zbic pawna z com[6] com[7]
                }

                if (commands[10].equals("yes")) {

                    //zmienic gracza
                    game.changePlayer();
                }

                if (commands[8].equals("yes")) {

                    //zamienic ruszanego pawna w krolowke
                    game.getPawn(1).setKing();
                }

                if (commands[9].equals("yes")) {

                    //koniec gry - wygrana gracza aktywnego
                }
            }
        }
    }
}
