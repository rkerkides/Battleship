package org.example;

import java.util.Scanner;

public class Player {
    private String name;
    private Board board;
    private int score;

    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        this.score++;
    }

    public boolean takeTurn(Scanner scanner) {
        System.out.println("Make a guess (x y):");
        String[] guess = scanner.nextLine().split(" ");
        int x = Integer.parseInt(guess[0]);
        int y = Integer.parseInt(guess[1]);

        Square square = board.getSquare(x, y);
        if (square.isHit()) {
            System.out.println("You already guessed that square!");
            return false;
        }

        if (square.hasShip()) {
            square.setHit(true);
            square.getBattleship().hit();
            if (square.getBattleship().isSunk()) {
                incrementScore();
                System.out.println("You sunk a battleship!");
                if (getScore() == 5) {
                    System.out.println("You won!");
                    return true;
                }
            } else {
                System.out.println("You hit a battleship!");
            }
        } else {
            square.setHit(true);
            System.out.println("You missed!");
        }
        return false;
    }
}
