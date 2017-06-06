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

  @BeforeAll
  static void buildUp() {

  }


  @DisplayName("empty")
  @Test
  void testEmpty() {
    BlockRegion r = BlockRegion.empty();
    System.out.println(r);
    assertFalse(r.contains(BlockPosition.of(-233,-233,-233)));
    assertFalse(r.contains(BlockPosition.of(237,356,812)));
  }

  @DisplayName("infinite")
  @Test
  void testInfinite() {
    BlockRegion r = BlockRegion.infinite();
    System.out.println(r);
    assertTrue(r.contains(BlockPosition.of(-233,-233,-233)));
    assertTrue(r.contains(BlockPosition.of(237,356,812)));
  }

  @DisplayName("flip")
  @Test
  void testFlip() {
    BlockRegion r = BlockRegion.cube(
            BlockPosition.of(10, 20, 30),
            BlockPosition.of(15, 22, 34)
    ).flip();
    assertEquals("true true true true true true true true true true",
            Stream.iterate(8, i -> i + 1).limit(10).map(i -> {
              BlockPosition p = BlockPosition.of(i, 19, 31);
              return r.contains(p);
            }).map(b -> b ? "true" : "false").map(s -> s + " ").reduce((a, b) -> a + b).orElse("").trim());
    assertEquals("true true false false false false false false true true",
            Stream.iterate(8, i -> i + 1).limit(10).map(i -> {
              BlockPosition p = BlockPosition.of(i, 20, 31);
              return r.contains(p);
            }).map(b -> b ? "true" : "false").map(s -> s + " ").reduce((a, b) -> a + b).orElse("").trim());
  }

  @DisplayName("cube")
  @Test
  void testCube() {
    {
      BlockRegion r = BlockRegion.cube(BlockPosition.of(10, 20, 30));
      assertAll(Stream.iterate(8, i -> i + 1).limit(5).map(i -> () -> {
        BlockPosition p = BlockPosition.of(i, 21, 30);
        assertFalse(r.contains(p));
      }));
      assertAll(Stream.iterate(8, i -> i + 1).limit(5).filter(i -> i != 10).map(i -> () -> {
        BlockPosition p = BlockPosition.of(i, 20, 30);
        assertFalse(r.contains(p));
      }));
      assertTrue(r.contains(BlockPosition.of(10, 20, 30)));
    }
    {
      BlockRegion r = BlockRegion.cube(
              BlockPosition.of(10, 20, 30),
              BlockPosition.of(15, 22, 34)
      );
      assertEquals("false false false false false false false false false false",
              Stream.iterate(8, i -> i + 1).limit(10).map(i -> {
                BlockPosition p = BlockPosition.of(i, 19, 31);
                return r.contains(p);
              }).map(b -> b ? "true" : "false").map(s -> s + " ").reduce((a, b) -> a + b).orElse("").trim());

      assertEquals("false false true true true true true true false false",
              Stream.iterate(8, i -> i + 1).limit(10).map(i -> {
                BlockPosition p = BlockPosition.of(i, 20, 31);
                return r.contains(p);
              }).map(b -> b ? "true" : "false").map(s -> s + " ").reduce((a, b) -> a + b).orElse("").trim());
    }
  }

}