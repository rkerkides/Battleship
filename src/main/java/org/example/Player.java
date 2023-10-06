package org.example;

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

    public void takeTurn() {

    }
}
