package com.example.checkers.server.model;

import javafx.scene.paint.Color;

import java.util.Map;

/**
 * The Frisian game rules
 */

public class FrisianGameLogic extends GameLogic {

    private boolean mustMove = false;
    private int kingMovesWhite = 0;
    private int kingMovesBlack = 0;

    public FrisianGameLogic(String name, String type, boolean bot) {
        super(name, type, bot);
    }

    @Override
    protected int setBoardSize() {
        return 10;
    }

    @Override
    protected String moveWhite(PawnData pawn, int x, int y) {

        String result = "";
        mustMove = false;

        if (pawn.isKing()) {

            if (kingMovesWhite < 3) {

                result = checkKillsForKing(pawn, x, y);

                if (result.length() < 3) {

                    result = kingMove(pawn, x, y);
                }
            } else {

                result = "no no x";
            }

        } else {

            result = checkKills(pawn, x, y);

            if (result.length() < 3) {

                result = "no no x";

                for (int i = -1; i <= 1; i += 2) {

                    if((pawn.getY() - 1 == y && pawn.getX() + i == x && (getPawn(x, y) == null || getPawn(x, y).isAlive())) && !mustMove) {

                        pawn.setX(x);
                        pawn.setY(y);
                        if (pawn.getColor().equals(Color.WHITE)) {
                            kingMovesWhite = 0;
                        } else if (pawn.getColor().equals(Color.BLACK)) {
                            kingMovesBlack = 0;
                        }
                        result = "yes no x";
                    }
                }
            }
        }

        return result;
    }

    @Override
    protected String moveBlack(PawnData pawn, int x, int y) {

        String result = "";
        mustMove = false;

        if (pawn.isKing()) {

            if (kingMovesBlack < 3) {

                result = checkKillsForKing(pawn, x, y);

                if (result.length() < 3) {

                    result = kingMove(pawn, x, y);
                }
            } else {

                result = "no no x";
            }

        } else {

            result = checkKills(pawn, x, y);

            if (result.length() < 3) {

                result = "no no x";

                for (int i = -1; i <= 1; i += 2) {

                    if ((pawn.getY() + 1 == y && pawn.getX() + i == x && (getPawn(x, y) == null || !getPawn(x, y).isAlive())) && !mustMove) {

                        pawn.setX(x);
                        pawn.setY(y);
                        if (pawn.getColor().equals(Color.WHITE)) {
                            kingMovesWhite = 0;
                        } else if (pawn.getColor().equals(Color.BLACK)) {
                            kingMovesBlack = 0;
                        }
                        result = "yes no x";
                    }
                }
            }
        }

        return result;
    }

    @Override
    protected boolean ifPlayerChange(PawnData pawn) {

        if (pawn.isKing()) {

            for (int i = -1; i <= 1; i += 2) {

                for (int j = -1; j <= 1; j += 2) {

                    if (ifKillIsPossibleForKing(pawn, i, j)) return false;
                }
            }
        }

        for (int i = -1; i <= 1; i += 2) {

            for (int j = -1; j <= 1; j += 2) {

                if (ifKillIsPossible(pawn, i, j)) return false;
            }
        }

        return true;
    }

    private boolean ifKillIsPossible(PawnData pawn, int i, int j) {

        PawnData pawnToCheck = getPawn(pawn.getX() + i, pawn.getY() + j);

        if ((pawnToCheck != null && pawnToCheck.isAlive()) && !pawnToCheck.getColor().equals(pawn.getColor())) {

            int mm = i < 0 ? i - 1 : i > 0 ? i + 1 : i;
            int nn = j < 0 ? j - 1 : j > 0 ? j + 1 : j;

            int xToCheck = pawn.getX() + mm;
            int yToCheck = pawn.getY() + nn;

            pawnToCheck = getPawn(xToCheck, yToCheck);

            if (((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (pawnToCheck == null || !pawnToCheck.isAlive())) {

                return true;
            }
        }

        return false;
    }

    private boolean ifKillIsPossibleForKing(PawnData pawn, int i, int j) {

        int mm = i < 0 ? i - 1 : i > 0 ? i + 1 : i;
        int nn = j < 0 ? j - 1 : j > 0 ? j + 1 : j;

        int xToCheck = pawn.getX() + i;
        int yToCheck = pawn.getY() + j;

        PawnData pawnToCheck = getPawn(xToCheck, yToCheck);

        while (((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (pawnToCheck == null || !pawnToCheck.isAlive())) {

            xToCheck = xToCheck + i;
            yToCheck = yToCheck + j;

            pawnToCheck = getPawn(xToCheck, yToCheck);
        }

        if (((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (pawnToCheck != null && pawnToCheck.isAlive()) && !pawnToCheck.getColor().equals(pawn.getColor())) {

            xToCheck = pawnToCheck.getX() + i;
            yToCheck = pawnToCheck.getY() + j;

            pawnToCheck = getPawn(xToCheck, yToCheck);

            if (((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (pawnToCheck == null || !pawnToCheck.isAlive())) {

                return true;
            }
        }

        return false;
    }

    private String checkKills(PawnData pawn, int x, int y) {

        PawnData pawnCurrent;

        for (Map.Entry<Integer, PawnData> entry : pawns.entrySet())  {

            if (entry.getValue().isAlive() && entry.getValue().getColor().equals(pawn.getColor())) {

                pawnCurrent = entry.getValue();

                for (int i = -1; i <= 1; i += 2) {

                    for (int j = -1; j <= 1; j += 2) {

                        PawnData pawnToCheck = getPawn(pawnCurrent.getX() + i, pawnCurrent.getY() + j);

                        if ((pawnToCheck != null && pawnToCheck.isAlive()) && !pawnToCheck.getColor().equals(pawnCurrent.getColor())) {

                            PawnData pawnToKill = pawnToCheck;

                            int mm = i < 0 ? i - 1 : i > 0 ? i + 1 : i;
                            int nn = j < 0 ? j - 1 : j > 0 ? j + 1 : j;

                            int xToCheck = pawnCurrent.getX() + mm;
                            int yToCheck = pawnCurrent.getY() + nn;

                            pawnToCheck = getPawn(xToCheck, yToCheck);

                            if (((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (pawnToCheck == null || !pawnToCheck.isAlive())) {

                                mustMove = true;

                                if (pawnCurrent.getKey() == pawn.getKey() && (xToCheck == x && yToCheck == y)) {

                                    pawn.setX(x);
                                    pawn.setY(y);
                                    pawnToKill.kill();
                                    if (pawn.getColor().equals(Color.WHITE)) {
                                        kingMovesWhite = 0;
                                    } else if (pawn.getColor().equals(Color.BLACK)) {
                                        kingMovesBlack = 0;
                                    }
                                    return "yes yes " + pawnToKill.getKey();
                                }
                            }
                        }
                    }
                }
            }
        }

        return "";
    }

    private String kingMove(PawnData pawn, int x, int y) {

        for (int i = -1; i <= 1; i += 2) {

            for (int j = -1; j <= 1; j += 2) {

                int mm = i < 0 ? i - 1 : i > 0 ? i + 1 : i;
                int nn = j < 0 ? j - 1 : j > 0 ? j + 1 : j;

                int xToCheck = pawn.getX() + i;
                int yToCheck = pawn.getY() + j;

                PawnData pawnToCheck = getPawn(xToCheck, yToCheck);

                while (((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (pawnToCheck == null || !pawnToCheck.isAlive())) {

                    if (((xToCheck == x && yToCheck == y) && (pawnToCheck == null || !pawnToCheck.isAlive())) && !mustMove) {

                        pawn.setX(x);
                        pawn.setY(y);
                        if (pawn.getColor().equals(Color.WHITE)) {
                            kingMovesWhite++;
                        } else if (pawn.getColor().equals(Color.BLACK)) {
                            kingMovesBlack++;
                        }
                        return "yes no x";
                    }

                    xToCheck = xToCheck + i;
                    yToCheck = yToCheck + j;

                    pawnToCheck = getPawn(xToCheck, yToCheck);
                }
            }
        }

        return "no no x";
    }

    private String checkKillsForKing(PawnData pawn, int x, int y) {

        PawnData pawnCurrent;
        checkKills(pawn, x, y);

        for (Map.Entry<Integer, PawnData> entry : pawns.entrySet()) {

            if ((entry.getValue().isAlive() && entry.getValue().getColor().equals(pawn.getColor())) && entry.getValue().isKing()) {

                pawnCurrent = entry.getValue();

                for (int i = -1; i <= 1; i += 2) {

                    for (int j = -1; j <= 1; j += 2) {

                        int mm = i < 0 ? i - 1 : i > 0 ? i + 1 : i;
                        int nn = j < 0 ? j - 1 : j > 0 ? j + 1 : j;

                        int xToCheck = pawnCurrent.getX() + i;
                        int yToCheck = pawnCurrent.getY() + j;

                        PawnData pawnToCheck = getPawn(xToCheck, yToCheck);

                        while (((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (pawnToCheck == null || !pawnToCheck.isAlive())) {

                            xToCheck = xToCheck + i;
                            yToCheck = yToCheck + j;

                            pawnToCheck = getPawn(xToCheck, yToCheck);
                        }

                        if (((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (pawnToCheck != null && pawnToCheck.isAlive())) {

                            PawnData pawnToKill = pawnToCheck;

                            xToCheck = pawnToKill.getX() + i;
                            yToCheck = pawnToKill.getY() + j;

                            pawnToCheck = getPawn(xToCheck, yToCheck);

                            if ((pawnCurrent.getKey() == pawn.getKey()) && ((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (pawnToCheck == null || !pawnToCheck.isAlive())) {

                                mustMove = true;

                                if (xToCheck == x && yToCheck == y) {

                                    pawn.setX(x);
                                    pawn.setY(y);
                                    pawnToKill.kill();
                                    if (pawn.getColor().equals(Color.WHITE)) {
                                        kingMovesWhite = 1;
                                    } else if (pawn.getColor().equals(Color.BLACK)) {
                                        kingMovesBlack = 1;
                                    }
                                    return "yes yes " + pawnToKill.getKey();
                                }
                            }
                        }
                    }
                }
            }
        }

        return "";
    }
}
