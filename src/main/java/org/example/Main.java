package org.example;

import java.util.Scanner;

public class Main {
    private static int playerCount = 0;  // Counter for player creation

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create two players
        Player player1 = createPlayer(scanner);
        Player player2 = createPlayer(scanner);

        boolean lastBattleshipSunk;  // Flag for game termination

        // Main game loop
        while (true) {
            // Handle player 1's turn
            takePlayerTurn(player1, scanner);
            lastBattleshipSunk = player1.takeTurn(scanner);
            if (lastBattleshipSunk) {
                System.out.println(player1.getName() + " wins!");
                break;
            }

            // Prompt to switch turns
            promptForNextTurn(scanner);

            // Handle player 2's turn
            takePlayerTurn(player2, scanner);
            lastBattleshipSunk = player2.takeTurn(scanner);
            if (lastBattleshipSunk) {
                System.out.println(player2.getName() + " wins!");
                break;
            }

            // Prompt to switch turns
            promptForNextTurn(scanner);
        }

        scanner.close();  // Close scanner to free resources
    }

    // Create a new player with a name and board
    private static Player createPlayer(Scanner scanner) {
        playerCount++;
        System.out.println("Enter player " + playerCount + "'s name:");
        String name = scanner.nextLine();
        Board board = new Board(10, 10);
        return new Player(name, board);
    }

    // Announce the current player's turn
    private static void takePlayerTurn(Player player, Scanner scanner) {
        System.out.println(player.getName() + "'s turn:");
    }

    // Prompt to continue to the next turn
    private static void promptForNextTurn(Scanner scanner) {
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }
}