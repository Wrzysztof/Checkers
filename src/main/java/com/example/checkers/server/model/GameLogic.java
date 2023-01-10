package com.example.checkers.server.model;

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
                    PawnData pawn = new PawnData(i, j, color);
                    pawns.put(pawnsCounter, pawn);
                    pawnsCounter++;
                }
            }
        }
    }

    protected abstract int setBoardSize();

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

        String toPrint = "r " + name + " ";

        int key = 0;
        int x = 0;
        int y = 0;
        boolean mustMove = false;
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

        if (getPawn(x, y) != null) {

            toPrint += "no";
            return toPrint;
        }

        if (pawn.getColor().equals(Color.WHITE) && player.equals("1") && playerWhite) {

            if (pawn.isKing()) {


            } else {

                for (int m = -1; m <= 1; m += 2) {

                    for (int n = -1; n <= 1; n += 2) {

                        if (getPawn(pawn.getX() + m, pawn.getY() + n) != null) {

                            PawnData pawnToKill = getPawn(pawn.getX() + m, pawn.getY() + n);
                            m = m < 0 ? m - 1 : m + 1;
                            n = n < 0 ? n - 1 : n + 1;

                            if (getPawn(pawn.getX() + m, pawn.getY() + n) == null) {

                                mustMove = true;

                                if (pawn.getX() + m == x && pawn.getY() + n == y) {

                                    pawn.setX(x);
                                    pawn.setY(y);
                                    pawnToKill.kill();
                                    toPrint += "yes ";
                                    toPrint += pawnToKill.getX();
                                    toPrint += " ";
                                    toPrint += pawnToKill.getY();
                                }
                            }
                        }
                    }
                }

                for(int i = -1; i <= 1; i++) {

                    if(pawn.getY() - 1 == y && pawn.getX() + i == x && getPawn(x, y) == null && !mustMove) {

                        pawn.setX(x);
                        pawn.setY(y);
                        toPrint += "yes ";
                    }
                }
            }

        } else if (pawn.getColor().equals(Color.BLACK) && player.equals("2") && !playerWhite) {

            if (pawn.isKing()) {


            } else {

                for(int i = -1; i <= 1; i++) {

                    if(pawn.getY() + 1 == y && pawn.getX() + i == x) {

                        pawn.setX(x);
                        pawn.setY(y);
                        toPrint += "yes";
                    }
                }
            }

        } else {

            toPrint += "no";
            return toPrint;
        }

        for (int m = -1; m <= 1; m += 2) {

            for (int n = -1; n <= 1; n += 2) {

                if (getPawn(pawn.getX() + m, pawn.getY() + n) != null) {

                    m = m < 0 ? m - 1 : m + 1;
                    n = n < 0 ? n - 1 : n + 1;

                    if (getPawn(pawn.getX() + m, pawn.getY() + n) == null) {

                        playerChange = false;
                    }
                }
            }
        }

        if (playerChange) {

            toPrint += " yes";
        } else {
            toPrint += " no";
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

            toPrint += " yes";

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

