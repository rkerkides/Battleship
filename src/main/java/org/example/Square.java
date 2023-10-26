package org.example;

public class Square {
    private final int row;  // Row index of the square
    private final int column;  // Column index of the square
    private boolean hasShip;  // Flag to indicate if a ship is placed on this square
    private Battleship battleship;  // Reference to the Battleship object placed on this square
    private boolean isHit;  // Flag to indicate if this square has been hit

    // Constructor to initialize the square
    public Square(int row, int column) {
        this.row = row;
        this.column = column;
        this.hasShip = false;
        this.battleship = null;
        this.isHit = false;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    // Check if the square has a ship
    public boolean hasShip() {
        return hasShip;
    }

    // Place a ship on the square
    public void placeShip(Battleship battleship) {
        this.hasShip = true;
        this.battleship = battleship;
    }

    // Get the Battleship object placed on this square
    public Battleship getBattleship() {
        return this.battleship;
    }

    // Check if the square has been hit
    public boolean isHit() {
        return isHit;
    }

    // Mark the square as hit
    public void setHit() {
        isHit = true;
    }

    // Generate a string representation of the square's state
    @Override
    public String toString() {
        if (isHit) {
            if (hasShip) {
                return String.format("%3s", "X");  // Hit and has a ship
            } else {
                return String.format("%3s", "O");  // Hit but no ship
            }
        } else {
            return String.format("%3s", "-");  // Not hit
        }
    }
}
