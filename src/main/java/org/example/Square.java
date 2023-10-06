package org.example;

public class Square {
    private int row;
    private int column;
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

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isHasShip() {
        return hasShip;
    }

    public void setHasShip(boolean hasShip) {
        this.hasShip = hasShip;
    }

    public Battleship getBattleship() {
        return battleship;
    }

    public void setBattleship(Battleship battleship) {
        this.battleship = battleship;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
