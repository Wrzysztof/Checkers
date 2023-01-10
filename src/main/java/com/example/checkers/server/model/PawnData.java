package com.example.checkers.server.model;

import javafx.scene.paint.Color;

public class PawnData {

    private int x;
    private int y;
    private boolean alive = true;
    private boolean king = false;
    private final Color color;

    public PawnData(int x, int y, Color color) {

        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void kill() {
        alive = false;
    }

    public void setKing() {

        king = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAlive() {
        return alive;
    }

    public Color getColor() {
        return color;
    }

    public boolean isKing() {

        return king;
    }
}
