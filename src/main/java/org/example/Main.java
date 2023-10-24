package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Board board = new Board(10, 10);

        // Create two players
        Player player1 = createPlayer(scanner, board);
        Player player2 = createPlayer(scanner, board);

        boolean lastBattleshipSunk;  // Flag for game termination

        // Main game loop
        while (true) {
            // Handle player 1's turn
            lastBattleshipSunk = player1.takeTurn(scanner, player2);
            if (lastBattleshipSunk) {
                break;
            }

            // Handle player 2's turn
            lastBattleshipSunk = player2.takeTurn(scanner, player1);
            if (lastBattleshipSunk) {
                break;
            }
        }

        scanner.close();  // Close scanner to free resources
    }

    // Create a new player with a name and board
    private static Player createPlayer(Scanner scanner, Board board) {
        // Prompt the user for the player's name
        // 1 added to playerCount because it is initialized to 0 before the first player is created
        System.out.println("Enter player " + (Player.playerCount + 1) + "'s name:");
        String name = scanner.nextLine();
        return new Player(name, board);
    }
}