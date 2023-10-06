package org.example;

public class Battleship {
    private boolean isSunk;
    private int remainingHealth;
    private final int size;

    public Battleship() {
        this.isSunk = false;
        this.size = 2;
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
}
