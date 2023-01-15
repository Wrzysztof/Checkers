package com.example.checkers.server.model;

/**
 * The Frisian game rules
 */

public class FrisianGameLogic extends GameLogic {

    private boolean mustMove = false;
    private int kingMoves = 0;

    public FrisianGameLogic(String name) {
        super(name);
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

            result = checkKillsForKing(pawn, x, y);

            if (result.length() < 3) {

                result += kingMove(pawn, x, y);
            }

        } else {

            result = checkKills(pawn, x, y);

            if (result.length() < 3) {

                for(int i = -1; i <= 1; i++) {

                    if((pawn.getY() - 1 == y && pawn.getX() + i == x && getPawn(x, y) == null) && !mustMove) {

                        pawn.setX(x);
                        pawn.setY(y);
                        result = "yes no x";
                        kingMoves = 0;
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

            for(int i = -1; i <= 1; i++) {

                if((pawn.getY() + 1 == y && pawn.getX() + i == x && getPawn(x, y) == null) && !mustMove) {

                    pawn.setX(x);
                    pawn.setY(y);
                    result = "yes no x";
                    kingMoves = 0;
                }
            }
        }

        return result;
    }

    @Override
    protected boolean ifPlayerChange(PawnData pawn) {

        for (int m = -1; m <= 1; m++) {

            for (int n = -1; n <= 1; n++) {

                if (m == 0 && n == 0) {

                    continue;
                }

                PawnData pawnToCheck = getPawn(pawn.getX() + m, pawn.getY() + n);;

                if (pawnToCheck != null && !pawnToCheck.getColor().equals(pawn.getColor())) {

                    int mm = m < 0 ? m - 1 : m > 0 ? m + 1 : m;
                    int nn = n < 0 ? n - 1 : n > 0 ? n + 1 : n;

                    if (getPawn(pawn.getX() + mm, pawn.getY() + nn) == null) {

                        return false;
                    }
                }
            }
        }

        return true;
    }

    private String checkKills(PawnData pawn, int x, int y) {

        String result = "";

        for (int m = -1; m <= 1; m++) {

            for (int n = -1; n <= 1; n++) {

                if (m == 0 && n == 0) {

                    continue;
                }

                PawnData pawnToCheck = getPawn(pawn.getX() + m, pawn.getY() + n);

                if (pawnToCheck != null && !pawnToCheck.getColor().equals(pawn.getColor())) {

                    PawnData pawnToKill = getPawn(pawn.getX() + m, pawn.getY() + n);
                    int mm = m < 0 ? m - 1 : m > 0 ? m + 1 : m;
                    int nn = n < 0 ? n - 1 : n > 0 ? n + 1 : n;

                    if (getPawn(pawn.getX() + mm, pawn.getY() + nn) == null) {

                        mustMove = true;

                        if (pawn.getX() + mm == x && pawn.getY() + nn == y) {

                            pawn.setX(x);
                            pawn.setY(y);
                            pawnToKill.kill();
                            result = "yes yes " + getPawn(pawnToKill.getX(), pawnToKill.getY()).getKey();
                            kingMoves = 0;
                        }
                    }
                }
            }
        }

        return result;
    }

    private String kingMove(PawnData pawn, int x, int y) {

        for (int i = -1; i <= 1; i++) {

            for (int j = -1; j <= 1; j++) {

                if (i == 0 && j == 0) {

                    continue;
                }

                int ii = 0;
                int jj = 0;

                for (int k = 0; k < 8; k++) {

                    if(((pawn.getY() + i + ii == x && pawn.getX() + j + jj == y && getPawn(x, y) == null) && !mustMove) && kingMoves < 3) {

                        pawn.setX(x);
                        pawn.setY(y);
                        kingMoves++;
                        return "yes no x";
                    }

                    ii += i;
                    jj += j;
                }
            }
        }

        return "";
    }

    private String checkKillsForKing(PawnData pawn, int x, int y) {

        String result = "";

        for (int i = -1; i <= 1; i++) {

            for (int j = -1; j <=1; j++) {

                if (i == 0 && j == 0) {

                    continue;
                }

                int ii = 0;
                int jj = 0;

                for (int k = 0; k < 8; k++) {

                    PawnData pawnToCheck = getPawn(pawn.getX() + i + ii, pawn.getY() + j + jj);

                    if (pawnToCheck != null && !pawnToCheck.getColor().equals(pawn.getColor())) {

                        PawnData pawnToKill = getPawn(pawn.getX() + i + ii, pawn.getY() + j + jj);

                        int mm = i + ii < 0 ? i + ii - 1 : i + ii > 0 ? i + ii + 1 : i + ii;
                        int nn = j + jj < 0 ? j + jj - 1 : j + jj > 0 ? j + jj + 1 : j + jj;

                        if (getPawn(pawn.getX() + mm, pawn.getY() + nn) == null && kingMoves < 3) {

                            mustMove = true;

                            if (pawn.getX() + mm == x && pawn.getY() + nn == y) {

                                pawn.setX(x);
                                pawn.setY(y);
                                pawnToKill.kill();
                                result = "yes yes " + getPawn(pawnToKill.getX(), pawnToKill.getY()).getKey();
                                kingMoves++;
                            }
                        }
                    }

                    ii += i;
                    jj += j;
                }
            }
        }

        return result;
    }
}
