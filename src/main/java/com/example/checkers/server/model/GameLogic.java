package com.example.checkers.server.model;

import com.example.checkers.client.view.boards.elements.Pawn;
import javafx.scene.paint.Color;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public abstract class GameLogic {

    private final String name;
    private final int boardSize;
    private boolean isStarted = false;
    private boolean playerWhite = false;
    private final HashMap<Integer, PawnData> pawns;

    public GameLogic(String name) {

        this.name = name;
        boardSize = setBoardSize();
        pawns = new HashMap<>();
        int pawnsCounter = 1;

        for (int i = 0; i < boardSize; i++) {

            for (int j = 0; j < boardSize; j++) {

                if(((i + j) % 2 == 0 && j < (boardSize / 2 - 1)) || ((i + j) % 2 == 0 && j > (boardSize / 2))) {

                    Color color = j > (boardSize / 2 - 1) ? Color.WHITE : Color.BLACK;
                    PawnData pawn = new PawnData(i, j, pawnsCounter, color);
                    pawns.put(pawnsCounter, pawn);
                    pawnsCounter++;
                }
            }
        }
    }

    protected abstract int setBoardSize();
    protected abstract String moveWhite(PawnData pawn, int x, int y);
    protected abstract String moveBlack(PawnData pawn, int x, int y);

    public String getName() {

        return name;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public PawnData getPawn(int key) {
        return pawns.get(key);
    }

    public PawnData getPawn(int x, int y) {

        PawnData pawn = null;

        for (Map.Entry<Integer, PawnData> entry : pawns.entrySet())  {

            if (entry.getValue().getX() == x && entry.getValue().getY() == y) {

                pawn = entry.getValue();
            }
        }
        return pawn;
    }

    public String doMove(String player, String number, String newX, String newY) {

        String toPrint = name + " ";

        int key = 0;
        int x = 0;
        int y = 0;
        boolean playerChange = true;
        boolean win = true;

        try {

            key = Integer.parseInt(number);
            x = Integer.parseInt(newX);
            y = Integer.parseInt(newY);

        } catch (NumberFormatException e) {

            e.printStackTrace();
        }

        PawnData pawn = pawns.get(key);

        toPrint += pawn.getKey() + " " + x + " " + y + " ";

        if (getPawn(x, y) != null) {

            toPrint += "no";
            return toPrint;
        }

        if ((pawn.getColor().equals(Color.WHITE) && player.equals("1")) && playerWhite) {

            String string = moveWhite(pawn, x, y);
            toPrint += string;

        } else if ((pawn.getColor().equals(Color.BLACK) && player.equals("2")) && !playerWhite) {

            String string = moveBlack(pawn, x, y);
            toPrint += string;

        } else {

            toPrint += "no";
            return toPrint;
        }

        //additional check - if player change - if capture is possible

        if (toPrint.charAt(toPrint.length() - 1) != 'x') {

            for (int m = -1; m <= 1; m += 2) {

                for (int n = -1; n <= 1; n += 2) {

                    PawnData pawnToCheck = getPawn(pawn.getX() + m, pawn.getY() + n);;

                    if (pawnToCheck != null && !pawnToCheck.getColor().equals(pawn.getColor())) {

                        int mm = m < 0 ? m - 1 : m + 1;
                        int nn = n < 0 ? n - 1 : n + 1;

                        if (getPawn(pawn.getX() + mm, pawn.getY() + nn) == null) {

                            playerChange = false;
                        }
                    }
                }
            }
        }

        if(pawn.getColor().equals(Color.WHITE) && pawn.getY() == 0) {

            pawn.setKing();
            toPrint += " yes";

        } else if (pawn.getColor().equals(Color.BLACK) && pawn.getY() == boardSize - 1) {

            pawn.setKing();
            toPrint += " yes";

        } else {

            toPrint += " no";
        }

        Color opponentColor = playerWhite ? Color.BLACK : Color.WHITE;

        for (Map.Entry<Integer, PawnData> entry : pawns.entrySet())  {

            if (entry.getValue().getColor().equals(opponentColor)) {

                win = false;
                break;
            }
        }

        if (win) {

            playerChange = false;
            toPrint += " yes";

        } else {

            toPrint += " no";
        }

        if (playerChange) {

            toPrint += " yes";
            playerWhite = !playerWhite;

        } else {

            toPrint += " no";
        }

        return toPrint;
    }

    public void startGame() {

        isStarted = true;
        playerWhite = true;
    }

    public boolean isStarted() {

        return isStarted;
    }
}

