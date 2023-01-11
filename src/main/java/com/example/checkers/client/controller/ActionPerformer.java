package com.example.checkers.client.controller;

import com.example.checkers.client.controller.buttons.ConnectionToEnglishGame;
import com.example.checkers.client.controller.buttons.ConnectionToFrisianGame;
import com.example.checkers.client.controller.buttons.ConnectionToGame;
import com.example.checkers.client.controller.buttons.ConnectionToPolishGame;
import com.example.checkers.client.controller.pawns.MovingPawns;
import com.example.checkers.client.view.boards.GameBoard;
import com.example.checkers.client.view.boards.PolishGameBoard;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.PrintWriter;

public final class ActionPerformer {

    private static PrintWriter out;
    private static GameBoard game;

    public static void setOut(PrintWriter outputPrinter) {

        out = outputPrinter;
    }

    public static void setGameBoard(GameBoard gameBoard) {

        game = gameBoard;
    }
    public static void check(String command) {

        String[] commands = command.split(" ");

        if (commands.length == 3) {

            ConnectionToGame.createRealGame(commands[0], commands[2], commands[1], out);

        } else if (commands.length == 2) {

            if (commands[0].equals("Polskie")) {

                ConnectionToPolishGame.setGame(commands[1]);

            } else if (commands[0].equals("Angielskie")) {

                ConnectionToEnglishGame.setGame(commands[1]);

            } else {

                ConnectionToFrisianGame.setGame(commands[1]);
            }
        }

        //tak wyglada komenda
        //name movekey movex movey ifmove ifkill killkey playerchange ifking ifwin

        else if (commands[0].equals(game.getName())) {

            if (commands[4].equals("yes")) {

                //wykonac ruch pawna z com[2] na com[3] com[4]
                int key = Integer.parseInt(commands[1]);
                int x = Integer.parseInt(commands[2]);
                int y = Integer.parseInt(commands[3]);
                MovingPawns.doMove(game.getPawn(key), x, y);

                if (commands[5].equals("yes")) {

                    //zbic pawna z com[7]
                    int keyKill = Integer.parseInt(commands[6]);

                    Platform.runLater(() -> game.getChildren().remove(game.getPawn(keyKill)));
                }

                if (commands[9].equals("yes")) {

                    //zmienic gracza
                    game.changePlayer();
                }

                if (commands[7].equals("yes")) {

                    //zamienic ruszanego pawna w krolowke
                    game.getPawn(key).setKing();
                }

                if (commands[8].equals("yes")) {

                    //koniec gry - wygrana gracza aktywnego
                }
            }
        }
    }
}
