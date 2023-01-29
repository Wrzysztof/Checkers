package com.example.checkers.server.model;

import java.util.ArrayList;
import java.util.Random;

public class BotLogic {

    private final ArrayList<Move> allMoves;
    private ArrayList<Move> currentMoves;
    private final ArrayList<Integer> allPawns;
    private ArrayList<Integer> currentPawns;
    private int pawn;
    private final Random rand = new Random();

    public BotLogic(GameLogic game) {

        allMoves = new ArrayList<>();
        allPawns = new ArrayList<>();
        int pawnsCounter = 1;

        for (int i = 0; i < game.getBoardSize(); i++) {

            for (int j = 0; j < game.getBoardSize(); j++) {

                Move move = new Move(i, j);
                allMoves.add(move);

                if(((i + j) % 2 == 0 && j > (game.getBoardSize() / 2))) {

                    pawnsCounter++;
                }

                if(((i + j) % 2 == 0 && j < (game.getBoardSize() / 2 - 1))) {

                    allPawns.add(pawnsCounter);
                    pawnsCounter++;
                }
            }
        }

        resetMoves();
        resetPawns();
        randomPawn();
    }

    public String doMove() {

        if (allPawns.size() < 1) {

            return "100 100 100";
        }

        System.out.println(currentMoves.size() + " teraz");
        if (currentMoves.size() < 1) {

            deletePawn(pawn);
            randomPawn();
            resetMoves();
            System.out.println(currentMoves.size() + " in");
        }

        System.out.println(currentMoves.size());
        int moveIndex = rand.nextInt(currentMoves.size());
        Move move = currentMoves.get(moveIndex);
        currentMoves.remove(moveIndex);

        return pawn + " " + move.getX() + " " + move.getY();
    }

    public void randomPawn() {

        pawn = allPawns.get(rand.nextInt(allPawns.size()));
    }

    public void resetMoves() {

        currentMoves = new ArrayList<>();

        for (Move move: allMoves) {

            Move newMove = new Move(move.getX(), move.getY());
            currentMoves.add(newMove);
        }
    }

    public void resetPawns() {

        currentPawns = new ArrayList<>();

        for (int pawn: allPawns) {

            currentPawns.add(pawn);
        }
    }

    public void deletePawn(int key) {

        int number = 0;

        for (int value : currentPawns) {

            if (value == key) {

                currentPawns.remove(number);
                break;
            }

            number++;
        }
    }

    public void killPawn(int key) {

        int number = 0;

        for (int value : allPawns) {

            if (value == key) {

                allPawns.remove(number);
                break;
            }

            number++;
        }
    }
}

class Move {

    private final int x;
    private final int y;

    Move(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
