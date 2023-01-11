package com.example.checkers.server.model;

public class PolishGameLogic extends GameLogic {

    private boolean mustMove = false;

    public PolishGameLogic(String name) {
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


        } else {

            result += checkKills(pawn, x, y);

            for(int i = -1; i <= 1; i++) {

                if(pawn.getY() - 1 == y && pawn.getX() + i == x && getPawn(x, y) == null && !mustMove) {

                    pawn.setX(x);
                    pawn.setY(y);
                    result += "yes no x";
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


        } else {

            result += checkKills(pawn, x, y);

            for(int i = -1; i <= 1; i++) {

                if(pawn.getY() + 1 == y && pawn.getX() + i == x && getPawn(x, y) == null && !mustMove) {

                    pawn.setX(x);
                    pawn.setY(y);
                    result += "yes";
                }
            }
        }

        return result;
    }

    public String checkKills(PawnData pawn, int x, int y) {

        String result = "";

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
                            result += "yes yes ";
                            result += getPawn(pawnToKill.getX(), pawnToKill.getY()).getKey();
                        }
                    }
                }
            }
        }

        return result;
    }
}
