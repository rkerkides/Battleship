package org.example;

import java.util.Scanner;

public class Main {
    private static int playerCount = 0;  // Counter for player creation

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
            takePlayerTurn(player1);
            lastBattleshipSunk = player1.takeTurn(scanner, player2);
            if (lastBattleshipSunk) {
                break;
            }

            // Prompt to switch turns
            promptForNextTurn(scanner);

            // Handle player 2's turn
            takePlayerTurn(player2);
            lastBattleshipSunk = player2.takeTurn(scanner, player1);
            if (lastBattleshipSunk) {
                break;
            }

            // Prompt to switch turns
            promptForNextTurn(scanner);
        }

        scanner.close();  // Close scanner to free resources
    }

    // Create a new player with a name and board
    private static Player createPlayer(Scanner scanner, Board board) {
        playerCount++;
        System.out.println("Enter player " + playerCount + "'s name:");
        String name = scanner.nextLine();
        return new Player(name, board);
    }

    // Announce the current player's turn
    private static void takePlayerTurn(Player player) {
        System.out.println(player.getName() + "'s turn:");
    }

    // Prompt to continue to the next turn
    private static void promptForNextTurn(Scanner scanner) {
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }
}