package com.snake1999.myserver.test.core;

import com.snake1999.myserver.core.BlockPosition;
import com.snake1999.myserver.core.BlockRegion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.Stream;

/**
 * Copyright 2017 lmlstarqaq
 * All rights reserved.
 */
@DisplayName("BlockRegion")
class BlockRegionTest {

  private static BlockRegion r1, r2, r3;

  @BeforeAll
  static void buildUp() {

  }

  @DisplayName("cube")
  @Test
  void testCube() {
    BlockRegion r = BlockRegion.cube(BlockPosition.of(10, 20, 30));
    assertAll(Stream.iterate(8, i -> i+1).limit(5).map(i -> () -> {
      BlockPosition p = BlockPosition.of(i, 21, 30);
      assertFalse(BlockRegion.contains(r, p));
    }));
    assertAll(Stream.iterate(8, i -> i+1).limit(5).filter(i -> i != 10).map(i -> () -> {
      BlockPosition p = BlockPosition.of(i, 20, 30);
      assertFalse(BlockRegion.contains(r, p));
    }));
    assertTrue(BlockRegion.contains(r, BlockPosition.of(10, 20, 30)));
  }

}