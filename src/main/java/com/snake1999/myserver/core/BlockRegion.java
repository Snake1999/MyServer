package com.snake1999.myserver.core;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.snake1999.myserver.core.Messages.*;

/**
 * Immutable class of block regions.
 * BlockRegion can be regarded as a multi-dimensional set of BlockPosition.
 *
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/5/20 12:06.
 */
public final class BlockRegion {

  public static BlockRegion empty() {
    return new BlockRegion(Collections.emptyMap(), BitSet.valueOf(new long[]{0x0}));
  }

  public static BlockRegion cube(BlockPosition... contained) {
    if(contained.length == 0) return empty();
    Set<Map.Entry<Dimension, Integer>> allData = Arrays.stream(contained)
            .flatMap(BlockPosition::data).collect(Collectors.toSet());
    Set<Dimension> allDimensions = allData.stream().map(Map.Entry::getKey).distinct().collect(Collectors.toSet());
    Map<Dimension, Set<Integer>> slices = allDimensions.stream().map(dimension -> Map.entry(dimension, Set.of(
            allData.stream().filter(entry -> entry.getKey() == dimension)
                    .map(Map.Entry::getValue).max(Integer::compareTo).map(v -> v + 1).orElse(0),
            allData.stream().filter(entry -> entry.getKey() == dimension)
                    .map(Map.Entry::getValue).min(Integer::compareTo).orElse(0)
            )))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    BitSet payload = new BitSet();
    payload.set(indexOfCube(allDimensions.size()));
    return new BlockRegion(slices, payload);
  }

  //    public static BlockRegion align(int x1, int x2, int y1, int y2, int z1, int z2) {
//
//    }
//
//  public static BlockRegion infinite() {
//
//  }
//
//
//  public static BlockRegion logicNot(BlockRegion range) {}
//
//  public static BlockRegion logicAnd(BlockRegion... ranges) {}
//
//  public static BlockRegion logicOr(BlockRegion... ranges) {}
//
//  public static BlockRegion logicXor(BlockRegion... ranges) {}
//
  public static boolean contains(BlockRegion region, BlockPosition position) {
    Objects.requireNonNull(region, block_region_can_not_be_null);
    Objects.requireNonNull(position, block_position_can_not_be_null);
    int index = indexOfPosition(region.slices, position);
    return region.buffer.get(index);
  }

  ///////////////////////////////////////////////////////////////////////////
  // Override
  ///////////////////////////////////////////////////////////////////////////

  @Override
  public String toString() {
    return slices.toString();
  }


  ///////////////////////////////////////////////////////////////////////////
  // Internal
  ///////////////////////////////////////////////////////////////////////////

  private SortedMap<Dimension, Set<Integer>> slices;
  private BitSet buffer;

  private BlockRegion(Map<Dimension, Set<Integer>> slices, BitSet buffer){
    this.slices = new TreeMap<>(Comparator.comparingInt(Enum::ordinal));
    this.slices.putAll(slices);
    this.buffer = buffer;
  }

  private static int indexOfCube(int dimensionCount) {
    return (int) (0.5d * (Math.pow(3d, dimensionCount) - 1d));
  }

  private static int indexOfPosition(SortedMap<Dimension, Set<Integer>> slices, BlockPosition p) {
    return p.data()
            .map(e -> sizeOfDimension(slices, e.getKey()) * (int)
                    (slices.get(e.getKey()).stream().filter(i -> i <= e.getValue()).count()))
            .reduce((a, b) -> a + b)
            .orElse(0);
  }

  private static int sizeOfDimension(SortedMap<Dimension, Set<Integer>> slices, Dimension d) {
    return slices.headMap(d).entrySet().stream()
            .map(e -> e.getValue().size() + 1)
            .reduce((a, b) -> a * b)
            .orElse(1);
  }


}
