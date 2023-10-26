package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleshipTest {

    private Battleship battleship;

    @BeforeEach
    void setUp() {
        Battleship.sunkCount = 0; // Reset the sunk count before each test
        battleship = new Battleship(3);
    }

    @Test
    void testBattleshipInitialization() {
        assertFalse(battleship.isSunk());
        assertEquals(3, battleship.getSize());
    }

    @Test
    void testHit() {
        battleship.hit(); // One hit, should not sink
        assertFalse(battleship.isSunk());

        battleship.hit(); // Two hits, still should not sink
        assertFalse(battleship.isSunk());

        battleship.hit(); // Third hit, should sink
        assertTrue(battleship.isSunk());
        assertEquals(1, Battleship.sunkCount);
    }

    @Test
    void testMultipleBattleships() {
        Battleship battleship2 = new Battleship(2);
        battleship2.hit(); // One hit, should not sink
        assertFalse(battleship2.isSunk());

        battleship2.hit(); // Second hit, should sink
        assertTrue(battleship2.isSunk());
        assertEquals(1, Battleship.sunkCount);

        battleship.hit(); // One hit, should not sink
        assertFalse(battleship.isSunk());

        battleship.hit(); // Two hits, still should not sink
        assertFalse(battleship.isSunk());

        battleship.hit(); // Third hit, should sink
        assertTrue(battleship.isSunk());
        assertEquals(2, Battleship.sunkCount);
    }
}
