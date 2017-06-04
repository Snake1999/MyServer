package com.snake1999.myserver.test;

import com.snake1999.myserver.core.BlockPosition;
import com.snake1999.myserver.core.BlockRange;

import java.util.stream.Stream;

/**
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/5/20 12:17.
 */
public final class BlockRangeTest {
  public static void main(String[] args) {
    BlockRange empty = BlockRange.empty();
    System.out.println(empty.toString());
    BlockRange infinite = BlockRange.infinite();
    System.out.println(infinite.toString());

    {
      BlockRange r = BlockRange.expandedCubeContains(
              BlockPosition.of(5, 24, 36),
              BlockPosition.of(10, 20, 30));

      System.out.println(r.toString());
      System.out.println(
              Stream.of(
                      Stream.iterate(4, i -> i+1).limit(10).map(i -> BlockPosition.of(i, 21, 35)),
                      Stream.iterate(19, i -> i+1).limit(10).map(i -> BlockPosition.of(7, i, 35)),
                      Stream.iterate(28, i -> i+1).limit(10).map(i -> BlockPosition.of(7, 21, i))
              ).reduce(Stream::concat).orElse(Stream.empty())
              .map(p1 -> String.format("%s: %b\n", p1.toString(), BlockRange.contains(r, p1)))
              .reduce(String::concat).orElse(""));
    }

    {
      BlockRange r = BlockRange.expandedCubeContains(
              BlockPosition.of(10, 25, 35),
              BlockPosition.of(10, 20, 30));

      System.out.println(r.toString());
      System.out.println(
              Stream.of(
                      Stream.iterate(7, i -> i+1).limit(10).map(i -> BlockPosition.of(i, 21, 31)),
                      Stream.iterate(17, i -> i+1).limit(10).map(i -> BlockPosition.of(10, i, 31)),
                      Stream.iterate(27, i -> i+1).limit(10).map(i -> BlockPosition.of(10, 21, i))
              ).reduce(Stream::concat).orElse(Stream.empty())
                      .map(p1 -> String.format("%s: %b\n", p1.toString(), BlockRange.contains(r, p1)))
                      .reduce(String::concat).orElse(""));
    }

  }

}
