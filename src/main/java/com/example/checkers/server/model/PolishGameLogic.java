package com.example.checkers.server.model;

import java.util.ArrayList;
import java.util.Map;

/**
 * The Polish game rules
 */

public class PolishGameLogic extends GameLogic {

    private boolean mustMove = false;
    private int bestKills = 0;
    private int currentKills = 0;
    private ArrayList<Integer> temporaryKilledPawns = new ArrayList<>();

    public PolishGameLogic(String name, String type, boolean bot) {
        super(name, type, bot);
    }

    @Override
    protected int setBoardSize() {
        return 10;
    }

    @Override
    protected String moveWhite(PawnData pawn, int x, int y) {

        String result;
        mustMove = false;
        bestKills = 0;

        if (pawn.isKing()) {

            result = checkKillsForKing(pawn, x, y);

            if (result.length() < 3) {

                result = kingMove(pawn, x, y);
            }

        } else {

            result = checkKills(pawn, x, y);

            if (result.length() < 3) {

                result = "no no x";

                for (int i = -1; i <= 1; i += 2) {

                    if ((pawn.getY() - 1 == y && pawn.getX() + i == x && (getPawn(x, y) == null || getPawn(x, y).isAlive())) && !mustMove) {

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

        String result;
        mustMove = false;
        bestKills = 0;

        if (pawn.isKing()) {

            result = checkKillsForKing(pawn, x, y);

            if (result.length() < 3) {

                result = kingMove(pawn, x, y);
            }

        } else {

            result = checkKills(pawn, x, y);

            if (result.length() < 3) {

                result = "no no x";

                for (int i = -1; i <= 1; i += 2) {

                    if ((pawn.getY() + 1 == y && pawn.getX() + i == x && (getPawn(x, y) == null || !getPawn(x, y).isAlive())) && !mustMove) {

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

            for (int i = -1; i <= 1; i += 2) {

                for (int j = -1; j <= 1; j += 2) {

                    if (ifKillIsPossibleForKing(pawn, i, j)) {
                        return false;
                    }
                }
            }

        } else {

            for (int i = -1; i <= 1; i += 2) {

                for (int j = -1; j <= 1; j += 2) {

                    if (ifKillIsPossible(pawn, i, j)) {
                        return false;
                    }
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

    private void findBestMove(PawnData pawn) {

        boolean first = true;
        int currentCurrentKills = 0;
        ArrayList<Integer> currentTemporaryKilledPawns = new ArrayList<>();

        if (pawn.isKing()) {

            for (int i = -1; i <= 1; i += 2) {

                for (int j = -1; j <= 1; j += 2) {

                    int mm = i < 0 ? i - 1 : i > 0 ? i + 1 : i;
                    int nn = j < 0 ? j - 1 : j > 0 ? j + 1 : j;

                    int xToCheck = pawn.getX() + i;
                    int yToCheck = pawn.getY() + j;

                    PawnData pawnToCheck = getPawn(xToCheck, yToCheck);

                    while (((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (pawnToCheck == null || (!pawnToCheck.isAlive() && !ifPawnIsNotTemporaryKilled(pawnToCheck.getKey())))) {

                        xToCheck = xToCheck + i;
                        yToCheck = yToCheck + j;

                        pawnToCheck = getPawn(xToCheck, yToCheck);
                    }

                    if (((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (((pawnToCheck != null && pawnToCheck.isAlive()) && ifPawnIsNotTemporaryKilled(pawnToCheck.getKey())) && !pawnToCheck.getColor().equals(pawn.getColor()))) {

                        PawnData pawnToKill = pawnToCheck;

                        xToCheck = pawnToKill.getX() + i;
                        yToCheck = pawnToKill.getY() + j;

                        pawnToCheck = getPawn(xToCheck, yToCheck);

                        if (((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (pawnToCheck == null || (!pawnToCheck.isAlive() && !ifPawnIsNotTemporaryKilled(pawnToCheck.getKey())))) {

                            if (!first) {
                                temporaryKilledPawns = currentTemporaryKilledPawns;
                                currentKills = currentCurrentKills;
                            }
                            currentTemporaryKilledPawns = temporaryKilledPawns;
                            currentCurrentKills = currentKills;
                            first = false;
                            temporaryKilledPawns.add(pawnToKill.getKey());
                            currentKills++;
                            findBestMove(new PawnData(xToCheck, yToCheck, 1000, pawn.getColor()));

                            if (currentKills > bestKills) {

                                bestKills = currentKills;
                                System.out.println(pawn.getX() + " " + pawn.getY());
                            }
                        }
                    }
                }
            }

        } else {

            for (int i = -1; i <= 1; i += 2) {

                for (int j = -1; j <= 1; j += 2) {

                    PawnData pawnToCheck = getPawn(pawn.getX() + i, pawn.getY() + j);

                    if (((pawnToCheck != null && pawnToCheck.isAlive()) && ifPawnIsNotTemporaryKilled(pawnToCheck.getKey())) && !pawnToCheck.getColor().equals(pawn.getColor())) {

                        PawnData pawnToKill = pawnToCheck;

                        int mm = i < 0 ? i - 1 : i > 0 ? i + 1 : i;
                        int nn = j < 0 ? j - 1 : j > 0 ? j + 1 : j;

                        int xToCheck = pawn.getX() + mm;
                        int yToCheck = pawn.getY() + nn;

                        pawnToCheck = getPawn(xToCheck, yToCheck);

                        if (((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (pawnToCheck == null || (!pawnToCheck.isAlive() || !ifPawnIsNotTemporaryKilled(pawnToCheck.getKey())))) {

                            if (!first) {
                                temporaryKilledPawns = currentTemporaryKilledPawns;
                                currentKills = currentCurrentKills;
                            }
                            currentCurrentKills = currentKills;
                            currentTemporaryKilledPawns = temporaryKilledPawns;
                            first = false;
                            temporaryKilledPawns.add(pawnToKill.getKey());
                            currentKills++;
                            findBestMove(new PawnData(xToCheck, yToCheck, 1000, pawn.getColor()));

                            if (currentKills > bestKills) {

                                bestKills = currentKills;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean ifPawnIsNotTemporaryKilled(int key) {

        for (int i : temporaryKilledPawns) {

            if (i == key) {

                return false;
            }
        }

        return true;
    }

    private String checkKills(PawnData pawn, int x, int y) {

        PawnData pawnCurrent;

        for (Map.Entry<Integer, PawnData> entry : pawns.entrySet())  {

            currentKills = 0;

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
                                currentKills++;
                                temporaryKilledPawns.add(pawn.getKey());
                                temporaryKilledPawns.add(pawnToKill.getKey());

                                findBestMove(new PawnData(x, y, 1000, pawn.getColor()));

                                if (currentKills > bestKills) {

                                    bestKills = currentKills;
                                }

                                temporaryKilledPawns.clear();
                                currentKills = 0;
                            }
                        }
                    }
                }
            }
        }

        for (int m = -1; m <= 1; m += 2) {

            for (int n = -1; n <= 1; n += 2) {

                PawnData pawnToCheck = getPawn(pawn.getX() + m, pawn.getY() + n);

                if ((pawnToCheck != null && pawnToCheck.isAlive()) && !pawnToCheck.getColor().equals(pawn.getColor())) {

                    PawnData pawnToKill = pawnToCheck;

                    int mm = m < 0 ? m - 1 : m + 1;
                    int nn = n < 0 ? n - 1 : n + 1;

                    int xToCheck = pawn.getX() + mm;
                    int yToCheck = pawn.getY() + nn;

                    pawnToCheck = getPawn(xToCheck, yToCheck);

                    if (((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (pawnToCheck == null || !pawnToCheck.isAlive())) {

                        currentKills++;
                        temporaryKilledPawns.add(pawn.getKey());
                        temporaryKilledPawns.add(pawnToKill.getKey());

                        findBestMove(new PawnData(x, y, 1000, pawn.getColor()));

                        System.out.println("zrobione: " + currentKills + "/" + bestKills);
                        if ((currentKills == bestKills) && (!pawn.isKing() && (xToCheck == x && yToCheck == y))) {

                            pawn.setX(x);
                            pawn.setY(y);
                            pawnToKill.kill();
                            return "yes yes " + pawnToKill.getKey();
                        }

                        currentKills = 0;
                        temporaryKilledPawns.clear();
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

                for (int i = 1; i > -2; i -= 2) {

                    for (int j = 1; j > -2; j -= 2) {

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

                            if (((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (pawnToCheck == null || !pawnToCheck.isAlive())) {

                                mustMove = true;
                                currentKills++;
                                temporaryKilledPawns.add(pawnCurrent.getKey());
                                temporaryKilledPawns.add(pawnToKill.getKey());

                                findBestMove(new PawnData(x, y, 1000, pawn.getColor()));

                                if (currentKills > bestKills) {

                                    bestKills = currentKills;
                                }

                                temporaryKilledPawns.clear();
                                currentKills = 0;
                            }
                        }
                    }
                }
            }
        }

        for (int i = -1; i <= 1; i += 2) {

            for (int j = -1; j <= 1; j += 2) {

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

                if (((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (pawnToCheck != null && pawnToCheck.isAlive())) {

                    PawnData pawnToKill = pawnToCheck;

                    xToCheck = pawnToKill.getX() + i;
                    yToCheck = pawnToKill.getY() + j;

                    pawnToCheck = getPawn(xToCheck, yToCheck);

                    if (((xToCheck >= 0 && xToCheck < getBoardSize()) && (yToCheck >= 0 && yToCheck < getBoardSize())) && (pawnToCheck == null || !pawnToCheck.isAlive())) {

                        currentKills++;
                        temporaryKilledPawns.add(pawn.getKey());
                        temporaryKilledPawns.add(pawnToKill.getKey());

                        findBestMove(new PawnData(x, y, 1000, pawn.getColor()));

                        System.out.println("zrobione: " + currentKills + "/" + bestKills);
                        if (currentKills == bestKills && (xToCheck == x && yToCheck == y)) {

                            pawn.setX(x);
                            pawn.setY(y);
                            pawnToKill.kill();
                            return "yes yes " + pawnToKill.getKey();
                        }

                        currentKills = 0;
                        temporaryKilledPawns.clear();
                    }
                }
            }
        }

        return "";
    }
}
