package org.example;

import java.util.Random;

public class Board {
    private final int rows;
    private final int columns;
    private final Square[][] squares;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.squares = new Square[rows][columns];  // Initialize the squares array
        placeSquares();
        generateBattleships();
    }

    // Initialize the board with squares
    private void placeSquares() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Square square = new Square(i, j);
                squares[i][j] = square;
            }
        }
    }

    // Generate battleships on the board
    private void generateBattleships() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            boolean isHorizontal = random.nextBoolean();
            int row, col;

            if (isHorizontal) {
                row = random.nextInt(rows);
                col = random.nextInt(columns - 1);  // Leave space for horizontal ship
            } else {
                row = random.nextInt(rows - 1);  // Leave space for vertical ship
                col = random.nextInt(columns);
            }

            // Place the ship
            squares[row][col].placeShip();
            if (isHorizontal) {
                squares[row][col + 1].placeShip();
            } else {
                squares[row + 1][col].placeShip();
            }
        }
    }

    // Get a square based on its row and column
    public Square getSquare(int row, int col) {
        return squares[row][col];
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();

        // Display column coordinates above the horizontal row
        board.append(" "); // One space for alignment
        for (int j = 0; j < columns; j++) {
            board.append("   ").append(j);
        }
        board.append("\n");

        // Display the board along with row coordinates on the left vertical column
        for (int i = 0; i < rows; i++) {
            board.append(i).append(" "); // Display row coordinate
            for (int j = 0; j < columns; j++) {
                board.append(squares[i][j]).append(" ");
            }
            board.append("\n");
        }
        return board.toString();
    }
}
