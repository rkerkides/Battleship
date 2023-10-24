package org.example;

import java.util.Scanner;

public class Player {
    public static int playerCount = 0;  // Static variable to keep track of the number of players
    private final String name;  // Name of the player
    private final Board board;  // The board that the player will play on
    private int score;  // Player's score

    // Constructor to initialize the player
    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
        this.score = 0;
        playerCount++;
    }

    // Get the player's name
    public String getName() {
        return name;
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
        System.out.println(this.name + "'s turn:");
        System.out.println(board);
        int[] coordinates = validateInputAndGetCoordinates(scanner);
        int x = coordinates[0];
        int y = coordinates[1];

        // Retrieve the square based on the player's guess
        Square square = board.getSquare(x, y);

        // Check if the square has already been hit
        if (square.isHit()) {
            System.out.println("You already guessed that square!");
            return false; // Signal that the game is not over and end the player's turn
        }

        // Check if the square has a ship
        if (square.hasShip()) {
            square.setHit();
            square.getBattleship().hit();

            // Check if the battleship is sunk
            if (square.getBattleship().isSunk()) {
                this.incrementScore();
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
                   return true; // Signal that the game has finished
                }
               // Print new scores if the game is not finished
               System.out.println(this.name + "'s score: " + this.score + "\n" +
                        otherPlayer.name + "'s score: " + otherPlayer.score);
            } else {
                System.out.println("You hit a battleship!");
            }
        } else {
            square.setHit();
            System.out.println("You missed!");
        }
        return false; // If 6 battleships have not yet been sunk,
        // signal that the game continues
    }

    // Validate input and get coordinates
    private int[] validateInputAndGetCoordinates(Scanner scanner) {
        int x = -1, y = -1;
        while (true) {
            System.out.println("Make a guess (x y):");
            String input = scanner.nextLine();

            if (input == null || input.trim().isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
                continue;
            }

            String[] guess = input.split(" ");
            if (guess.length != 2) {
                System.out.println("Invalid format. Enter coordinates as 'x y'. Please try again.");
                continue;
            }

            try {
                x = Integer.parseInt(guess[1]);
                y = Integer.parseInt(guess[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Coordinates should be integers. Please try again.");
                continue;
            }

            if (x < 0 || x >= 10 || y < 0 || y >= 10) {
                System.out.println("Invalid coordinates. They should be between 0 and 9. Please try again.");
                continue;
            }
            break;
        }
        return new int[]{x, y};
    }
}
