package com.example.checkers.server.model;

import java.util.Map;

/**
 * The English game rules
 */


public class EnglishGameLogic extends GameLogic {

    private boolean mustMove = false;

    public EnglishGameLogic(String name, String type) {
        super(name, type);
    }

    @Override
    protected int setBoardSize() {
        return 8;
    }

    @Override
    protected String moveWhite(PawnData pawn, int x, int y) {

        String result;
        mustMove = false;

        if (pawn.isKing()) {

            result = checkKillsForKing(pawn, x, y);

            if (result.length() < 3) {

                result = kingMove(pawn, x, y);
            }

        } else {

            result = checkKills(pawn, x, y);

            if (result.length() < 3) {

                result = "no no x";

                for(int i = -1; i <= 1; i++) {

                    if((pawn.getY() - 1 == y && pawn.getX() + i == x && (getPawn(x, y) == null || getPawn(x, y).isAlive())) && !mustMove) {

                        pawn.setX(x);
                        pawn.setY(y);
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

            result = checkKillsForKing(pawn, x, y);

            if (result.length() < 3) {

                result += kingMove(pawn, x, y);
            }

        } else {

            result = checkKills(pawn, x, y);

            if (result.length() < 3) {

                result = "no no x";

                for(int i = -1; i <= 1; i++) {

                    if((pawn.getY() + 1 == y && pawn.getX() + i == x && (getPawn(x, y) == null || !getPawn(x, y).isAlive())) && !mustMove) {

                        pawn.setX(x);
                        pawn.setY(y);
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

            for (int i = -1; i <= 1; i++) {

                for (int j = -1; j <= 1; j++) {

                    if (i == 0 && j == 0) {

                        continue;
                    }

                    if (ifKillIsPossible(pawn, i, j)) return false;
                }
            }

        } else {

            for (int i = -1; i <= 1; i += 2) {

                for (int j = -1; j <= 1; j += 2) {

                    if (ifKillIsPossible(pawn, i, j)) return false;
                }
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

    private String checkKills(PawnData pawn, int x, int y) {

        PawnData pawnCurrent;

        for (Map.Entry<Integer, PawnData> entry : pawns.entrySet())  {

            if (entry.getValue().isAlive() && entry.getValue().getColor().equals(pawn.getColor())) {

                pawnCurrent = entry.getValue();

                for (int m = -1; m <= 1; m += 2) {

                    for (int n = -1; n <= 1; n += 2) {

                        PawnData pawnToCheck = getPawn(pawnCurrent.getX() + m, pawnCurrent.getY() + n);

                        if ((pawnToCheck != null && pawnToCheck.isAlive()) && !pawnToCheck.getColor().equals(pawnCurrent.getColor())) {

                            PawnData pawnToKill = pawnToCheck;

                            int mm = m < 0 ? m - 1 : m + 1;
                            int nn = n < 0 ? n - 1 : n + 1;

                            int xToCheck = pawnCurrent.getX() + mm;
                            int yToCheck = pawnCurrent.getY() + nn;

                            pawnToCheck = getPawn(xToCheck, yToCheck);

                            if (((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (pawnToCheck == null || !pawnToCheck.isAlive())) {

                                mustMove = true;

                                if (!pawn.isKing() && (pawnCurrent.getKey() == pawn.getKey() && (xToCheck == x && yToCheck == y))) {

                                    pawn.setX(x);
                                    pawn.setY(y);
                                    pawnToKill.kill();
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

        for (int i = -1; i <= 1; i++) {

            for (int j = -1; j <=1; j++) {

                if (i == 0 && j == 0) {

                    continue;
                }

                if((pawn.getY() + i == x && pawn.getX() + j == y && (getPawn(x, y) == null || !getPawn(x, y).isAlive())) && !mustMove) {

                    pawn.setX(x);
                    pawn.setY(y);
                    return "yes no x";
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

                for (int i = -1; i <= 1; i++) {

                    for (int j = -1; j <= 1; j++) {

                        if (i == 0 && j == 0) {

                            continue;
                        }

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
