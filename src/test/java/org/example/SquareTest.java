package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    private Square square;

    @BeforeEach
    void setUp() {
        square = new Square(5, 5);
    }

    @Test
    void testSquareInitialization() {
        assertFalse(square.hasShip());
        assertNull(square.getBattleship());
        assertFalse(square.isHit());
    }

    @Test
    void testPlaceShip() {
        Battleship battleship = new SmallBattleship();
        square.placeShip(battleship);
        assertTrue(square.hasShip());
        assertEquals(battleship, square.getBattleship());
    }

    @Test
    void testSetHit() {
        square.setHit();
        assertTrue(square.isHit());
    }

    @Test
    void testToString() {
        assertEquals("  -", square.toString());  // Not hit, no ship
        square.setHit();
        assertEquals("  O", square.toString());  // Hit, no ship
        square.placeShip(new SmallBattleship());
        assertEquals("  X", square.toString());  // Hit, has ship
    }
}
