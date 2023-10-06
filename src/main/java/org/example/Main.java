package org.example;

import java.util.Scanner;

public class Main {
    // Counter to keep track of the number of players created
    private static int playerCount = 0;

    public static void main(String[] args) {
        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Create players
        Player player1 = createPlayer(scanner);
        Player player2 = createPlayer(scanner);

        boolean lastBattleshipSunk = false;

        while (true) {  // Infinite loop, will break when the last battleship is sunk
            // Player 1's turn
            System.out.println(player1.getName() + "'s turn:");
            lastBattleshipSunk = player1.takeTurn(scanner);
            if (lastBattleshipSunk) {
                break;
            }

            // Player 2's turn
            System.out.println(player2.getName() + "'s turn:");
            lastBattleshipSunk = player2.takeTurn(scanner);
            if (lastBattleshipSunk) {
                break;
            }
        }
    }

    private static Player createPlayer(Scanner scanner) {
        // Increment player counter
        playerCount++;

        // Prompt user for player name
        System.out.println("Enter player " + playerCount + "'s name:");
        String name = scanner.nextLine();

        // Create a new board for the player
        Board board = new Board(10, 10);

        // Create and return new player
        return new Player(name, board);
    }
}
