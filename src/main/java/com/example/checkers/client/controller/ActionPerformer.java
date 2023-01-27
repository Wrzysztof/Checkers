package com.example.checkers.client.controller;

import com.example.checkers.client.controller.buttons.ConnectionToEnglishGame;
import com.example.checkers.client.controller.buttons.ConnectionToFrisianGame;
import com.example.checkers.client.controller.buttons.ConnectingToGame;
import com.example.checkers.client.controller.buttons.ConnectionToPolishGame;
import com.example.checkers.client.controller.pawns.MovingPawns;
import com.example.checkers.client.view.GameEndWindow;
import com.example.checkers.client.view.boards.GameBoard;
import javafx.application.Platform;

import java.io.PrintWriter;

/**
 * Receiving information about the game from the server and changing the boards
 */

public final class ActionPerformer {

    private static PrintWriter out;
    private static GameBoard game;

    public static void setOut(PrintWriter outputPrinter) {

        out = outputPrinter;
    }

    public static void setGameBoard(GameBoard gameBoard) {

        game = gameBoard;
    }

    /**
     * Performing action depending on the message read
     * @param command Message from the server
     */

    public static void check(String command) {

        String[] commands = command.split(" ");

        if (commands.length == 4) {

            ConnectingToGame.createRealGame(commands[0], commands[2], commands[1], commands[3], out);

        } else if (commands.length == 2) {

            if (commands[0].equals("Polskie")) {

                ConnectionToPolishGame connect = new ConnectionToPolishGame();
                connect.setGame(commands[1]);

            } else if (commands[0].equals("Angielskie")) {

                ConnectionToEnglishGame connect = new ConnectionToEnglishGame();
                connect.setGame(commands[1]);

            } else {

                ConnectionToFrisianGame connect = new ConnectionToFrisianGame();
                connect.setGame(commands[1]);
            }
        }

        //commmand
        //name movekey movex movey ifmove ifkill killkey playerchange ifking ifwin

        else if (commands[0].equals(game.getName())) {

            if (commands[4].equals("yes")) {

                //move pawn com[1] to com[2] com[3]
                int key = Integer.parseInt(commands[1]);
                int x = Integer.parseInt(commands[2]);
                int y = Integer.parseInt(commands[3]);
                MovingPawns.doMove(game.getPawn(key), x, y);

                if (commands[5].equals("yes")) {

                    //kill pawn com[6]
                    int keyKill = Integer.parseInt(commands[6]);
                    Platform.runLater(() -> game.getChildren().remove(game.getPawn(keyKill)));
                }

                if (commands[9].equals("yes")) {

                    game.changePlayer();
                }

                if (commands[7].equals("yes")) {

                    game.getPawn(key).setKing();
                }

                if (!commands[8].equals("no")) {

                    Platform.runLater(() -> GameEndWindow.display(commands[8]));
                }
            }
        }
    }
}
