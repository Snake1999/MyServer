package com.snake1999.myserver.test.core;

import com.snake1999.myserver.core.BlockIdentifier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/6/5 12:58.
 */
@DisplayName("BlockIdentifier class")
class BlockIdentifierTest {

    private static BlockIdentifier bid, bid2, bid3;

    @BeforeAll
    static void buildUp() {
        bid = BlockIdentifier.ofStringId("my_category:my_block");
        bid2 = BlockIdentifier.ofStringId("my_category:my_block");
        bid3 = BlockIdentifier.ofStringId("my_category:my_block_2");
    }


    @DisplayName("equals method")
    @Test
    void testEquals() {
        assertEquals(bid, bid2);
        assertNotEquals(bid, bid3);
        assertTrue(BlockIdentifier.equals(bid, bid2));
        assertFalse(BlockIdentifier.equals(bid, bid3));
    }

    @DisplayName("toString method")
    @Test
    void testToString() {
        assertEquals("BlockIdentifier[my_category:my_block]", bid.toString());
    }

    @DisplayName("hashCode method")
    @Test
    void testHashcode() {
        assertEquals(-777400477, bid.hashCode());
    }

    @DisplayName("getStringId method")
    @Test
    void testGetStringId() {
        assertEquals("my_category:my_block", bid2.getStringId());
    }

}