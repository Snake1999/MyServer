package com.snake1999.myserver.api;

import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

import static com.snake1999.myserver.api.API.Definition.UNIVERSAL;
import static com.snake1999.myserver.api.API.Usage.BLEEDING;
import static com.snake1999.myserver.api.Messages.*;

/**
 * Immutable class declaring positions of blocks.
 *
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/5/19 13:00.
 */
@API(usage = BLEEDING, definition = UNIVERSAL)
public final class BlockPosition {

  @API(usage = BLEEDING, definition = UNIVERSAL)
  public static BlockPosition of(int blockX, int blockY, int blockZ) {
    return new BlockPosition(Map.of(Dimension.X, blockX, Dimension.Y, blockY, Dimension.Z, blockZ));
  }

  @API(usage = BLEEDING, definition = UNIVERSAL)
  public static boolean equals(BlockPosition a, BlockPosition b) {
    Objects.requireNonNull(a, block_position_can_not_be_null);
    Objects.requireNonNull(b, block_position_can_not_be_null);
    return Objects.equals(a.payload, b.payload);
  }

  @API(usage = BLEEDING, definition = UNIVERSAL)
  public int getBlockX() {
    return payload.get(Dimension.X);
  }

  @API(usage = BLEEDING, definition = UNIVERSAL)
  public int getBlockY() {
    return payload.get(Dimension.Y);
  }

  @API(usage = BLEEDING, definition = UNIVERSAL)
  public int getBlockZ() {
    return payload.get(Dimension.Z);
  }

  @API(usage = BLEEDING, definition = UNIVERSAL)
  public BlockPosition setValue(Dimension dimension, int newValue) {
    Objects.requireNonNull(dimension, dimension_can_not_be_null);
    return copyAndModifyValue(this, dimension, newValue, v -> newValue);
  }

  @API(usage = BLEEDING, definition = UNIVERSAL)
  public BlockPosition addValue(Dimension dimension, int deltaValue) {
    Objects.requireNonNull(dimension, dimension_can_not_be_null);
    return copyAndModifyValue(this, dimension, deltaValue, v -> v + deltaValue);
  }

  @API(usage = BLEEDING, definition = UNIVERSAL)
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
    return Objects.hashCode(payload);
  }

  @Override
  public String toString() {
    return String.format("BlockPosition[x=%d, y=%d, z=%d]", getBlockX(), getBlockY(), getBlockZ());
  }

  ///////////////////////////////////////////////////////////////////////////
  // Internal
  ///////////////////////////////////////////////////////////////////////////

  // package-local
  Stream<Map.Entry<Dimension, Integer>> data() {
    return payload.entrySet().stream();
  }

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
