package com.snake1999.myserver.test;

import com.snake1999.myserver.api.BlockPosition;
import com.snake1999.myserver.api.Dimension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/6/5 12:19.
 */
@DisplayName("BlockPosition")
class BlockPositionTest {

  private static BlockPosition p1, p2, p3;

  @BeforeAll
  static void buildUp() {
    p1 = BlockPosition.of(-10, 10, 20);
    p2 = BlockPosition.of(-11, 9, 18).addValueXYZ(1, 1, 2);
    p3 = BlockPosition.of(10, 5, 6).addValueXYZ(0, 0, 1);
  }

  @DisplayName("addValue")
  @Test
  void testAddValue() {
    BlockPosition p4, p5;
    p4 = BlockPosition.of(1, 2, 3);
    p5 = BlockPosition.of(-5, 4, 3);
    p5 = p5.addValue(Dimension.X, 6);
    p5 = p5.addValue(Dimension.Y, -2);
    assertEquals(p4, p5);
  }

  @DisplayName("setValue")
  @Test
  void testSetValue() {
    BlockPosition p4, p5;
    p4 = BlockPosition.of(6, 9, 20);
    p5 = BlockPosition.of(6, 4, 3);
    p5 = p5.setValue(Dimension.Z, 20);
    p5 = p5.setValue(Dimension.Y, 9);
    assertEquals(p4, p5);
  }

  @DisplayName("getX getY and getZ")
  @Test
  void testGetXYZ() {
    assertEquals(String.format("x=%d, y=%d, z=%d", p3.getBlockX(), p3.getBlockY(), p3.getBlockZ()),
            "x=10, y=5, z=7");
  }

  @DisplayName("equals")
  @Test
  void testEquals() {
    assertAll(
            () -> assertEquals(p1, p2),
            () -> assertNotEquals(p2, p3),
            () -> assertTrue(BlockPosition.equals(p1, p2)),
            () -> assertFalse(BlockPosition.equals(p2, p3))
    );
  }

  @DisplayName("toString")
  @Test
  void testToString() {
    assertEquals(p1.toString(), "BlockPosition[x=-10, y=10, z=20]");
  }

  @DisplayName("hashCode")
  @Test
  void testHashcode() {
    assertEquals(p2.hashCode(), p1.hashCode());
  }


}