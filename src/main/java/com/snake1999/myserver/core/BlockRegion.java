package com.snake1999.myserver.core;

import java.util.*;
import java.util.stream.Collectors;

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
    return new BlockRegion(Collections.emptyMap(), new BitSet(0));
  }

  public static BlockRegion infinite() {
    return empty().flip();
  }

  public static BlockRegion align(int x1, int x2, int y1, int y2, int z1, int z2) {
    return cube(BlockPosition.of(x1, y1, z1), BlockPosition.of(x2, y2, z2));
  }

  public static BlockRegion cube(BlockPosition... contained) {
    Objects.requireNonNull(contained, block_position_can_not_be_null);
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

//  public static BlockRegion and(BlockRegion... regions) {}
//
//  public static BlockRegion or(BlockRegion... regions) {}
//
//  public static BlockRegion xor(BlockRegion... regions) {}

  public boolean contains(BlockPosition position) {
    Objects.requireNonNull(position, block_position_can_not_be_null);
    return contains(this, position);
  }

  public BlockRegion flip() {
    return flip(this);
  }

  ///////////////////////////////////////////////////////////////////////////
  // Override
  ///////////////////////////////////////////////////////////////////////////

  @Override
  public boolean equals(Object obj) {
    return obj instanceof BlockRegion &&
            Objects.equals(((BlockRegion) obj).slices, this.slices) &&
            Objects.equals(((BlockRegion) obj).buffer, this. buffer);
  }

  @Override
  public int hashCode() {
    return slices.hashCode() ^ buffer.hashCode();
  }

  @Override
  public String toString() {
    return String.format("BlockRegion{slices = %s, buffer = %s}", slices, buffer);
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

  private static BlockRegion flip(BlockRegion region) {
    BitSet newPayload = (BitSet) region.buffer.clone();
    newPayload.flip(0, maxIndex(region.slices) + 1);
    return new BlockRegion(region.slices, newPayload);
  }

  private static boolean contains(BlockRegion region, BlockPosition position) {
    int index = indexOfPosition(region.slices, position);
    return region.buffer.get(index);
  }

  private static int maxIndex(SortedMap<Dimension, Set<Integer>> slices) {
    return slices.entrySet().stream().map(e -> e.getValue().size() + 1).reduce((a, b) -> a * b).orElse(0);
  }

  private static int indexOfCube(int dimensionCount) {
    return (int) (0.5d * (Math.pow(3d, dimensionCount) - 1d));
  }

  private static int indexOfPosition(SortedMap<Dimension, Set<Integer>> slices, BlockPosition p) {
    if(slices.isEmpty()) return 0;
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
