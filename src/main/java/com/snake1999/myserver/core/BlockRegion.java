package com.snake1999.myserver.core;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Immutable class of block regions.
 * BlockRegion can be regarded as a multi-dimensional set of BlockPosition.
 *
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/5/20 12:06.
 */
public final class BlockRegion {

//  public static BlockRegion cube(BlockPosition... contained) {
//    if(contained.length == 0) return empty();
//    List<BlockPosition> positionList = Arrays.asList(contained);
//
//  }

//    public static BlockRegion align(int x1, int x2, int y1, int y2, int z1, int z2) {
//
//    }
//
//  public static BlockRegion infinite() {
//
//  }
//
  public static BlockRegion empty() {
    return new BlockRegion(SliceContainer.empty(), BitSet.valueOf(new long[]{0x0}));
  }
//
//  public static BlockRegion logicNot(BlockRegion range) {}
//
//  public static BlockRegion logicAnd(BlockRegion... ranges) {}
//
//  public static BlockRegion logicOr(BlockRegion... ranges) {}
//
//  public static BlockRegion logicXor(BlockRegion... ranges) {}
//
//  public static boolean contains(BlockRegion range, BlockPosition position) {
//
//  }

  ///////////////////////////////////////////////////////////////////////////
  // Override
  ///////////////////////////////////////////////////////////////////////////



  ///////////////////////////////////////////////////////////////////////////
  // Internal
  ///////////////////////////////////////////////////////////////////////////

  private SliceContainer slices;
  private BitSet buffer;

  private BlockRegion(SliceContainer slices, BitSet buffer){
    this.slices = slices;
    this.buffer = buffer;
  }

  private static class SliceContainer extends ArrayList<Set<Integer>> {
    static SliceContainer empty() {
      return new SliceContainer();
    }
  }

}
