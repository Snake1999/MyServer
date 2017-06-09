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
@DisplayName("BlockIdentifier")
class BlockIdentifierTest {

  private static BlockIdentifier id1, id2, id3;

  @BeforeAll
  static void buildUp() {
    id1 = BlockIdentifier.ofStringId("my_category:my_block");
    id2 = BlockIdentifier.ofStringId("my_category:my_block");
    id3 = BlockIdentifier.ofStringId("my_category:my_block_2");
  }

  @DisplayName("equals")
  @Test
  void testEquals() {
    assertAll(
            () -> assertEquals(id1, id2),
            () -> assertNotEquals(id1, id3),
            () -> assertTrue(BlockIdentifier.equals(id1, id2)),
            () -> assertFalse(BlockIdentifier.equals(id1, id3))
    );
  }

  @DisplayName("toString")
  @Test
  void testToString() {
    assertEquals("BlockIdentifier[my_category:my_block]", id1.toString());
  }

  @DisplayName("hashCode")
  @Test
  void testHashcode() {
    assertAll(
            () -> assertEquals(id2.hashCode(), id1.hashCode()),
            () -> assertEquals(BlockIdentifier.ofStringId("my_category:my_block_2").hashCode(), id3.hashCode())
    );
  }

  @DisplayName("stringId")
  @Test
  void testStringId() {
    assertEquals("my_category:my_block", id2.stringId());
  }

}