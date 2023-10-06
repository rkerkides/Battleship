package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private final int rows;
    private final int columns;
    private ArrayList<Square> squares;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public void placeSquares(int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns ; j++) {
                Square square = new Square(i, j);
                squares.add(square);
            }
        }
    }

    public void generateBattleships() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            boolean isHorizontal = random.nextBoolean();
            int verticalPosition;
            int horizontalPosition;

            if (isHorizontal) {
                verticalPosition = random.nextInt(this.columns - 1);
                horizontalPosition = random.nextInt(this.rows - 2);
            } else {
                verticalPosition = random.nextInt(this.columns - 2);
                horizontalPosition = random.nextInt(this.rows - 1);
            }
            squares.get(verticalPosition + horizontalPosition).setHasShip(true);
        }
    }
}
