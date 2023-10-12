package org.example;

public class Battleship {
    public static int sunkCount = 0; // Static variable to keep track of the number of sunk battleships
    private boolean isSunk;  // Flag to indicate if the battleship is sunk
    private int remainingHealth;  // Remaining health of the battleship
    private final int size;  // Size of the battleship

    // Constructor to initialize the battleship
    public Battleship(int size) {
        this.isSunk = false;
        this.size = size;
        this.remainingHealth = size;  // Initially, remaining health is equal to size
    }

    // Method to handle a hit on the battleship
    public void hit() {
        this.remainingHealth--;  // Decrement remaining health

        // Check if the battleship is sunk
        if (this.remainingHealth == 0) {
            this.isSunk = true;
            Battleship.sunkCount++;  // Increment the number of sunk battleships
        }
    }

    // Check if the battleship is sunk
    public boolean isSunk() {
        return isSunk;
    }

    // Get the size of the battleship
    public int getSize() {
        return size;
    }
}
