package org.example;

public class Square {
    private final int row;
    private final int column;
    private boolean hasShip;
    private Battleship battleship;
    private boolean isHit;

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

    public boolean hasShip() {
        return hasShip;
    }

    public void placeShip(Battleship battleship) {
        this.hasShip = true;
        this.battleship = battleship;
    }

    public Battleship getBattleship() {
        return this.battleship;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit() {
        isHit = true;
    }

    @Override
    public String toString() {
        if (isHit) {
            if (hasShip) {
                return String.format("%3s", "X");
            } else {
                return String.format("%3s", "O");
            }
        } else {
            return String.format("%3s", "-");
        }
    }
}
