package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(10, 10);
    }

    @Test
    void testBoardInitialization() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Square square = board.getSquare(i, j);
                assertNotNull(square);
                assertEquals(i, square.getRow());
                assertEquals(j, square.getColumn());
                assertFalse(square.isHit());
            }
        }
    }

    @Test
    void testGetSquare() {
        Square square = board.getSquare(5, 5);
        assertNotNull(square);
        assertEquals(5, square.getRow());
        assertEquals(5, square.getColumn());
    }

    @Test
    void testToString() {
        String boardString = board.toString();
        assertNotNull(boardString);
        assertTrue(boardString.contains("0")); // Check if the board contains row number 0
        assertTrue(boardString.contains("9")); // Check if the board contains column number 9
    }

}
