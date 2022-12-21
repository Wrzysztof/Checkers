package com.example.checkers.client.model;

import javafx.scene.paint.Color;

public class PawnData {

    private int x;
    private int y;
    private boolean alive;
    private final Color color;

    public PawnData(int x, int y, Color color) {

        this.x = x;
        this.y = y;
        this.alive = true;
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void kill() {
        this.alive = false;
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
}
