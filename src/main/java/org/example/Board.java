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
        int smallCounter = 0, mediumCounter = 0, largeCounter = 0;

        // Loop until all battleships are placed
        while (true) {
            int size;
            // Determine the size of the next battleship to place
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

            // Create and place the battleship.
            createBattleship(size);
        }
    }

    // Create a battleship of a given size
    private void createBattleship(int size) {
        Random random = new Random();
        while (true) {
            // Determine the orientation of the battleship (horizontal or vertical)
            boolean isHorizontal = random.nextBoolean();
            int row, col;

            // Generate random starting coordinates for the battleship
            if (isHorizontal) {
                // If the ship is horizontal, it can start at any row but must have enough columns to fit
                row = random.nextInt(rows);
                col = random.nextInt(columns - size + 1);
            } else {
                // If the ship is vertical, it can start at any column but must have enough rows to fit
                row = random.nextInt(rows - size + 1);
                col = random.nextInt(columns);
            }

            // Check if the generated position is valid for placing the battleship
            if (isPositionValid(row, col, isHorizontal, size)) {
                // Create a new battleship of the appropriate type based on its size
                Battleship battleship = switch (size) {
                    case 1 -> new SmallBattleship();
                    case 2 -> new MediumBattleship();
                    case 3 -> new LargeBattleship();
                    default -> throw new IllegalArgumentException("Invalid size");
                };
                // Place the battleship on the board at the generated coordinates
                placeShip(row, col, isHorizontal, battleship);
                break;
            }
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
        // Loop to place each segment of the battleship
        for (int i = 0; i < battleship.getSize(); i++) {
            squares[row][col].placeShip(battleship);
            
            // Update row or column index for the next loop iteration based on ship orientation
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
        // Create numbered column headers for a better user experience
        // Start with a space for alignment of the column headers
        board.append(" ");

        // Append column numbers with appropriate spacing for readability
        for (int j = 0; j < columns; j++) {
            // Each column number is prefixed with three spaces for alignment
            board.append("   ").append(j);
        }
        // New line to separate column headers from the board rows
        board.append("\n");

        // Loop through each row and column to append the square's state to the board string
        for (int i = 0; i < rows; i++) {
            // Display row numbers with appropriate spacing for readability
            board.append(i).append(" ");
            for (int j = 0; j < columns; j++) {
                // Append the square's state to the board string
                board.append(squares[i][j]).append(" ");
            }
            // New line to separate rows
            board.append("\n");
        }
        return board.toString();
    }
}
