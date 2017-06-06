package com.snake1999.myserver.core;

import java.util.*;
import java.util.function.IntUnaryOperator;

import static com.snake1999.myserver.core.Messages.*;

/**
 * Immutable class declaring positions of blocks.
 *
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/5/19 13:00.
 */
public final class BlockPosition {

  public static BlockPosition of(int blockX, int blockY, int blockZ) {
    return new BlockPosition(Map.of(Dimension.X, blockX, Dimension.Y, blockY, Dimension.Z, blockZ));
  }

  public static boolean equals(BlockPosition a, BlockPosition b) {
    Objects.requireNonNull(a, block_position_can_not_be_null);
    Objects.requireNonNull(b, block_position_can_not_be_null);
    return Objects.equals(a.payload, b.payload);
  }

  public int getBlockX() {
    return payload.get(Dimension.X);
  }

  public int getBlockY() {
    return payload.get(Dimension.Y);
  }

  public int getBlockZ() {
    return payload.get(Dimension.Z);
  }

  public BlockPosition setValue(Dimension dimension, int value) {
    Objects.requireNonNull(dimension, dimension_can_not_be_null);
    return copyAndModifyValue(this, dimension, value, v -> value);
  }

  public BlockPosition addValue(Dimension dimension, int value) {
    Objects.requireNonNull(dimension, dimension_can_not_be_null);
    return copyAndModifyValue(this, dimension, value, v -> v + value);
  }

  public BlockPosition addValueXYZ(int deltaX, int deltaY, int deltaZ) {
    return new BlockPosition(Map.of(
            Dimension.X, getBlockX() + deltaX,
            Dimension.Y, getBlockY() + deltaY,
            Dimension.Z, getBlockZ() + deltaZ
    ));
  }

  ///////////////////////////////////////////////////////////////////////////
  // Override
  ///////////////////////////////////////////////////////////////////////////

  @Override
  public boolean equals(Object obj) {
    return obj instanceof BlockPosition && equals(this, (BlockPosition) obj);
  }

  @Override
  public int hashCode() {
    return this.payload.values().stream().map(i -> Integer.hashCode(i)).reduce((a, b) -> a^b).orElse(0);
  }

  @Override
  public String toString() {
    return String.format("BlockPosition[x=%d, y=%d, z=%d]", getBlockX(), getBlockY(), getBlockZ());
  }

  ///////////////////////////////////////////////////////////////////////////
  // Internal
  ///////////////////////////////////////////////////////////////////////////

  private static BlockPosition copyAndModifyValue(BlockPosition origin,
                                                  Dimension dimension,
                                                  int defaultValue,
                                                  IntUnaryOperator modifier) {
    Map<Dimension, Integer> newPayload = new HashMap<>(origin.payload);
    newPayload.putIfAbsent(dimension, defaultValue);
    newPayload.computeIfPresent(dimension, (k, v) -> modifier.applyAsInt(v));
    return new BlockPosition(newPayload);
  }

  private Map<Dimension, Integer> payload;

  private BlockPosition(Map<Dimension, Integer> payload) {
    this.payload = payload;
  }

}
