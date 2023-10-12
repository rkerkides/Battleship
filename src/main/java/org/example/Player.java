package org.example;

import java.util.Scanner;

public class Player {
    private final String name;  // Name of the player
    private final Board board;  // Board associated with the player
    private int score;  // Player's score

    // Constructor to initialize the player
    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
        this.score = 0;
    }

    // Get the player's name
    public String getName() {
        return name;
    }

    // Get the player's board
    public Board getBoard() {
        return board;
    }

    // Get the player's score
    public int getScore() {
        return score;
    }

    // Increment the player's score
    public void incrementScore() {
        this.score++;
    }

    // Handle the player's turn
    public boolean takeTurn(Scanner scanner, Player otherPlayer) {
        System.out.println(board);
        System.out.println("Make a guess (x y):");
        String[] guess = scanner.nextLine().split(" ");
        int x = Integer.parseInt(guess[1]);
        int y = Integer.parseInt(guess[0]);

        // Retrieve the square based on the player's guess
        Square square = board.getSquare(x, y);

        // Check if the square has already been hit
        if (square.isHit()) {
            System.out.println(board);
            System.out.println("You already guessed that square!");
            return false;
        }

        // Check if the square has a ship
        if (square.hasShip()) {
            square.setHit();
            square.getBattleship().hit();

            // Check if the battleship is sunk
            if (square.getBattleship().isSunk()) {
                this.incrementScore();
                System.out.println(board);
                System.out.println("You sunk a battleship!");

                // Check for game win condition
               if (Battleship.sunkCount == 6) {
                    if (this.score > otherPlayer.getScore()) {
                        System.out.println("Congratulations, " + this.name + ", you won with " + this.score +
                                " points compared to " + otherPlayer.getName() + "'s " + otherPlayer.getScore() + " points!");
                    } else if (this.score == otherPlayer.getScore()) {
                        System.out.println("It's a tie! You both have 3 points.");
                    } else {
                        System.out.println("Congratulations, " + otherPlayer.getName() + ", you won with " + otherPlayer.getScore() +
                                " points compared to " + this.name + "'s " + this.score + " points!");
                    }
                }
            } else {
                System.out.println(board);
                System.out.println("You hit a battleship!");
            }
        } else {
            square.setHit();
            System.out.println(board);
            System.out.println("You missed!");
        }
        return false;
    }
}
