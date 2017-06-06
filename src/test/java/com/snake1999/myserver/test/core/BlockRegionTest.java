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

  private static BlockRegion empty, infinite, r1, r2, r3;

  @BeforeAll
  static void buildUp() {
    empty = BlockRegion.empty();
    infinite = BlockRegion.infinite();
    r1 = BlockRegion.cube(
            BlockPosition.of(10, 20, 30),
            BlockPosition.of(15, 22, 34)
    );
    r2 = r1.flip();
    r3 = BlockRegion.cube(BlockPosition.of(10, 20, 30));
  }

  @DisplayName("empty")
  @Test
  void testEmpty() {
    assertFalse(empty.contains(BlockPosition.of(-233,-233,-233)));
    assertFalse(empty.contains(BlockPosition.of(237,356,812)));
  }

  @DisplayName("infinite")
  @Test
  void testInfinite() {
    assertTrue(infinite.contains(BlockPosition.of(-233,-233,-233)));
    assertTrue(infinite.contains(BlockPosition.of(237,356,812)));
  }

  @DisplayName("flip")
  @Test
  void testFlip() {
    BlockRegion rf = r2.flip(); // rf = r2.flip() = r1.flip().flip() = r1s
    assertAll(
            () -> assertEquals("1111111111",
                    Stream.iterate(8, i -> i + 1).limit(10).map(i -> r2.contains(BlockPosition.of(i, 19, 31)))
                            .map(b -> b ? "1" : "0").reduce((a, b) -> a + b).orElse("")),
            () -> assertEquals("1100000011",
                    Stream.iterate(8, i -> i + 1).limit(10).map(i -> r2.contains(BlockPosition.of(i, 20, 31)))
                            .map(b -> b ? "1" : "0").reduce((a, b) -> a + b).orElse("")),
            () -> assertEquals("0000000000",
                    Stream.iterate(8, i -> i + 1).limit(10).map(i -> rf.contains(BlockPosition.of(i, 19, 31)))
                            .map(b -> b ? "1" : "0").reduce((a, b) -> a + b).orElse("")),
            () -> assertEquals("0011111100",
                    Stream.iterate(8, i -> i + 1).limit(10).map(i -> rf.contains(BlockPosition.of(i, 20, 31)))
                            .map(b -> b ? "1" : "0").reduce((a, b) -> a + b).orElse(""))
    );
  }

  @DisplayName("cube")
  @Test
  void testCube() {
    assertAll(
            () -> assertAll(Stream.iterate(8, i -> i + 1).limit(5).map(i -> () ->
                    assertFalse(r3.contains(BlockPosition.of(i, 21, 30))))),
            () -> assertAll(Stream.iterate(8, i -> i + 1).limit(5).filter(i -> i != 10).map(i -> () ->
                    assertFalse(r3.contains(BlockPosition.of(i, 20, 30))))),
            () -> assertTrue(r3.contains(BlockPosition.of(10, 20, 30))),
            () -> assertEquals("0000000000",
                    Stream.iterate(8, i -> i + 1).limit(10).map(i ->
                            r1.contains(BlockPosition.of(i, 19, 31)))
                            .map(b -> b ? "1" : "0").reduce((a, b) -> a + b).orElse("")),
            () -> assertEquals("0011111100",
                    Stream.iterate(8, i -> i + 1).limit(10).map(i ->
                            r1.contains(BlockPosition.of(i, 20, 31)))
                            .map(b -> b ? "1" : "0").reduce((a, b) -> a + b).orElse(""))
    );
  }

  @DisplayName("toString")
  @Test
  void testToString() {
    assertAll(
            () -> assertEquals("BlockRegion{slices = {}, buffer = {}}", empty.toString()),
            () -> assertEquals("BlockRegion{slices = {}, buffer = {0}}", infinite.toString())
    );
  }

  @DisplayName("hashCode")
  @Test
  void testHashCode() {
    assertAll(
            () -> assertEquals(empty.hashCode(), infinite.flip().hashCode()),
            () -> assertEquals(r1.hashCode(), r2.flip().hashCode()),
            () -> assertEquals(1542083049, r3.hashCode())
    );
  }

}