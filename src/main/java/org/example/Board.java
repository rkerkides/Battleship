package org.example;

import java.util.Random;

public class Board {
    private final int rows;
    private final int columns;
    private final Square[][] squares;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.squares = new Square[rows][columns];
        placeSquares();
        generateBattleships();
    }

    private void placeSquares() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                squares[i][j] = new Square(i, j);
            }
        }
    }

    private void generateBattleships() {
        Random random = new Random();
        int smallCounter = 0, mediumCounter = 0, largeCounter = 0;

        while (true) {
            int size;
            if (smallCounter < SmallBattleship.permissibleNumber) {
                size = 1;
                smallCounter++;
            } else if (mediumCounter < MediumBattleship.permissibleNumber) {
                size = 2;
                mediumCounter++;
            } else if (largeCounter < LargeBattleship.permissibleNumber) {
                size = 3;
                largeCounter++;
            } else {
                break;
            }

            createBattleship(random, size);
        }
    }

    private void createBattleship(Random random, int size) {
        boolean isHorizontal = random.nextBoolean();
        int row, col;

        if (isHorizontal) {
            row = random.nextInt(rows);
            col = random.nextInt(columns - size + 1);
        } else {
            row = random.nextInt(rows - size + 1);
            col = random.nextInt(columns);
        }

        if (isPositionValid(row, col, isHorizontal, size)) {
            Battleship battleship;
            switch (size) {
                case 1:
                    battleship = new SmallBattleship();
                    break;
                case 2:
                    battleship = new MediumBattleship();
                    break;
                case 3:
                    battleship = new LargeBattleship();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid size");
            }
            placeShip(row, col, isHorizontal, battleship);
        }
    }


    private boolean isPositionValid(int row, int col, boolean isHorizontal, int size) {
        for (int i = 0; i < size; i++) {
            if (squares[row][col].hasShip()) {
                return false;
            }
            if (isHorizontal) {
                col++;
            } else {
                row++;
            }
        }
        return true;
    }

    private void placeShip(int row, int col, boolean isHorizontal, Battleship battleship) {
        for (int i = 0; i < battleship.getSize(); i++) {
            squares[row][col].placeShip(battleship);
            if (isHorizontal) {
                col++;
            } else {
                row++;
            }
        }
    }

    public Square getSquare(int row, int col) {
        return squares[row][col];
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        board.append(" ");
        for (int j = 0; j < columns; j++) {
            board.append("   ").append(j);
        }
        board.append("\n");

        for (int i = 0; i < rows; i++) {
            board.append(i).append(" ");
            for (int j = 0; j < columns; j++) {
                board.append(squares[i][j]).append(" ");
            }
            board.append("\n");
        }
        return board.toString();
    }
}
