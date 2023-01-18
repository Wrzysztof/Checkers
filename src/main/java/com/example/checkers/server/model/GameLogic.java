package com.example.checkers.server.model;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * The game rules
 */

public abstract class GameLogic {

    private final String name;
    private final String type;
    private final int boardSize;
    private boolean isStarted = false;
    private boolean playerWhite = false;
    protected HashMap<Integer, PawnData> pawns;

    public GameLogic(String name, String type) {

        this.name = name;
        this.type = type;
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
    protected abstract boolean ifPlayerChange(PawnData pawn);

    public String getName() {

        return name;
    }

    public String getType() {

        return type;
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

            if ((entry.getValue().getX() == x && entry.getValue().getY() == y) && entry.getValue().isAlive()) {

                pawn = entry.getValue();
            }
        }
        return pawn;
    }

    /**
     * Checking if move can be done and what would happen after
     * @param player Player performing move
     * @param number Key of the pawn moved
     * @param newX Position x on which pawn is moved
     * @param newY Position y on which pawn is moved
     * @return Message what to do to send to the players
     */

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

        toPrint += key + " " + x + " " + y + " ";

        if (getPawn(x, y) != null && getPawn(x, y).isAlive()) {

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

        if (toPrint.length() > 9 && toPrint.charAt(toPrint.length() - 1) != 'x') {

            playerChange = ifPlayerChange(pawn);
        }

        if (toPrint.charAt(toPrint.length() - 6) == 'o') {

            playerChange = false;
        }

        if (!pawn.isKing() && playerChange) {

            if (pawn.getColor().equals(Color.WHITE) && pawn.getY() == 0) {

                pawn.setKing();
                toPrint += " yes";

            } else if (pawn.getColor().equals(Color.BLACK) && pawn.getY() == boardSize - 1) {

                pawn.setKing();
                toPrint += " yes";

            } else {

                toPrint += " no";
            }

        } else {

            toPrint += " no";
        }

        Color opponentColor = playerWhite ? Color.BLACK : Color.WHITE;

        for (Map.Entry<Integer, PawnData> entry : pawns.entrySet())  {

            if (entry.getValue().getColor().equals(opponentColor) && entry.getValue().isAlive()) {

                win = false;
                break;
            }
        }

        if (win) {

            if (playerWhite) {

                toPrint += " Biały";
            } else {
                toPrint += " Czarny";
            }

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

