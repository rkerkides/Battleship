package org.example;

public class Battleship {
    private boolean isSunk;
    private int remainingHealth;
    private final int size;

    public Battleship(int size) {
        this.isSunk = false;
        this.size = size;
        this.remainingHealth = size;
    }

    public void hit() {
        this.remainingHealth--;
        if (this.remainingHealth == 0) {
            this.isSunk = true;
        }
    }

    public boolean isSunk() {
        return isSunk;
    }

    public int getSize() {
        return size;
    }
}
