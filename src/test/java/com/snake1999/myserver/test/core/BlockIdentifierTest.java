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

  private static BlockIdentifier id1, id2, id3;

  @BeforeAll
  static void buildUp() {
    id1 = BlockIdentifier.ofStringId("my_category:my_block");
    id2 = BlockIdentifier.ofStringId("my_category:my_block");
    id3 = BlockIdentifier.ofStringId("my_category:my_block_2");
  }

  @DisplayName("equals method")
  @Test
  void testEquals() {
    assertEquals(id1, id2);
    assertNotEquals(id1, id3);
    assertTrue(BlockIdentifier.equals(id1, id2));
    assertFalse(BlockIdentifier.equals(id1, id3));
  }

  @DisplayName("toString method")
  @Test
  void testToString() {
    assertEquals("BlockIdentifier[my_category:my_block]", id1.toString());
  }

  @DisplayName("hashCode method")
  @Test
  void testHashcode() {
    assertEquals(-777400477, id1.hashCode());
    assertEquals(-777400477, id2.hashCode());
    assertEquals(242454102, id3.hashCode());
  }

  @DisplayName("getStringId method")
  @Test
  void testGetStringId() {
    assertEquals("my_category:my_block", id2.getStringId());
  }

}