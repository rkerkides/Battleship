package org.example;

import java.util.Random;

public class Board {
    private final int rows;  // Number of rows on the board
    private final int columns;  // Number of columns on the board
    private final Square[][] squares;  // 2D array to represent the squares on the board

    // Constructor to initialize the board
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.squares = new Square[rows][columns];
        placeSquares();
        generateBattleships();
    }

    // Initialize squares on the board
    private void placeSquares() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                squares[i][j] = new Square(i, j);
            }
        }
    }

    // Generate battleships on the board
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

    // Create a battleship of a given size
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
            Battleship battleship = switch (size) {
                case 1 -> new SmallBattleship();
                case 2 -> new MediumBattleship();
                case 3 -> new LargeBattleship();
                default -> throw new IllegalArgumentException("Invalid size");
            };
            System.out.println(row + " " + col + " " + isHorizontal + " " + battleship);
            placeShip(row, col, isHorizontal, battleship);
        }
    }

    // Check if the position is valid for placing a battleship
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

    // Place the battleship on the board
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

    // Retrieve a square at a specific row and column
    public Square getSquare(int row, int col) {
        return squares[row][col];
    }

    // Generate a string representation of the board
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
