package org.example;

public class SmallBattleship extends Battleship {
    // Constant to define the permissible number of SmallBattleships in the game
    public static final int permissibleNumber = 3;

    // Constructor to initialize the SmallBattleship with a size of 1
    public SmallBattleship() {
        super(1);  // Call the super constructor with size 1
    }
}
