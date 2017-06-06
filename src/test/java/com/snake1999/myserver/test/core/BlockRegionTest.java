package com.snake1999.myserver.test.core;

import com.snake1999.myserver.core.BlockPosition;
import com.snake1999.myserver.core.BlockRegion;
import com.snake1999.myserver.core.Dimension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.stream.Stream;

/**
 * Copyright 2017 lmlstarqaq
 * All rights reserved.
 */
@DisplayName("BlockRegion class")
class BlockRegionTest {

  private static BlockRegion r1, r2, r3;

  @BeforeAll
  static void buildUp() {

  }

  @Test
  void test() {
//    BlockRegion r = new BlockRegion(Map.of(
//            Dimension.X, Set.of(10, 13),
//            Dimension.Y, Set.of(22, 24),
//            Dimension.Z, Set.of(31, 35)
//    ), new BitSet());
//    Arrays.stream(Dimension.values()).forEach(d ->
//            System.out.println(String.format("%s - %d", d.toString(), r.sizeOfDimension(d))));
//    Stream.iterate(8, i -> i+1).limit(10).forEach(i -> {
//      BlockPosition p = BlockPosition.of(i, 24, 0);
//      System.out.println(p + " - "+r.indexOfPosition(p));
//    });
    BlockRegion r = BlockRegion.cube(BlockPosition.of(10, 20, 30));
    System.out.println(r);
    Stream.iterate(8, i -> i+1).limit(5).forEach(i -> {
      BlockPosition p = BlockPosition.of(i, 20, 30);
      System.out.println(p + " - "+ BlockRegion.contains(r, p));
    });

  }

}