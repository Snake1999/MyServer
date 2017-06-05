package com.snake1999.myserver.test.core;

import com.snake1999.myserver.core.BlockPosition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/6/5 12:19.
 */
@DisplayName("BlockPosition class")
class BlockPositionTest {

    private static BlockPosition p1, p2, p3;

    @BeforeAll
    static void buildUp() {
        p1 = BlockPosition.of(-10, 10, 20);
        p2 = BlockPosition.of(-11, 9, 18).addXYZ(1, 1, 2);
        p3 = BlockPosition.of(10, 5, 6).addXYZ(0, 0, 1);
    }

    @DisplayName("getX getY and getZ methods")
    @Test
    void testGetXYZ() {
        assertEquals(String.format("x=%d, y=%d, z=%d", p3.getBlockX(), p3.getBlockY(), p3.getBlockZ()),
                "x=10, y=5, z=7");
    }

    @DisplayName("equals method")
    @Test
    void testEquals() {
        assertEquals(p1, p2);
        assertNotEquals(p2, p3);
        assertTrue(BlockPosition.equals(p1, p2));
        assertFalse(BlockPosition.equals(p2, p3));
    }

    @DisplayName("toString method")
    @Test
    void testToString() {
        assertEquals(p1.toString(), "BlockPosition[x=-10, y=10, z=20]");
    }

    @DisplayName("hashCode method")
    @Test
    void testHashcode() {
        assertEquals(-24, p1.hashCode());
        assertEquals(-24, p2.hashCode());
        assertEquals(8, p3.hashCode());
    }


}