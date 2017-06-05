package com.snake1999.myserver.core;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Immutable class declaring positions of blocks.
 *
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/5/19 13:00.
 */
public final class BlockPosition {

  public static BlockPosition of(int blockX, int blockY, int blockZ) {
    return new BlockPosition(List.of(blockX, blockY, blockZ));
  }

  public static boolean equals(BlockPosition a, BlockPosition b) {
    Objects.requireNonNull(a, Messages.block_position_can_not_be_null);
    Objects.requireNonNull(b, Messages.block_position_can_not_be_null);
    return Objects.equals(a.payload, b.payload);
  }

  public int getBlockX() {
    return payload.get(Dimensions.DIMENSION_X);
  }

  public int getBlockY() {
    return payload.get(Dimensions.DIMENSION_Y);
  }

  public int getBlockZ() {
    return payload.get(Dimensions.DIMENSION_Z);
  }

  public BlockPosition addXYZ(int deltaX, int deltaY, int deltaZ) {
    return copyAndAddPayload(deltaX, deltaY, deltaZ);
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
    return this.payload.stream().map(i -> Integer.hashCode(i)).reduce((a, b) -> a^b).orElse(0);
  }

  @Override
  public String toString() {
    return String.format("BlockPosition[x=%d, y=%d, z=%d]", getBlockX(), getBlockY(), getBlockZ());
  }

  ///////////////////////////////////////////////////////////////////////////
  // Internal
  ///////////////////////////////////////////////////////////////////////////

  List<Integer> payload;

  private BlockPosition(List<Integer> payload) {
    this.payload = payload;
  }

  private BlockPosition copyAndAddPayload(int... payload) {
    List<Integer> target = Arrays.stream(payload).boxed().collect(Collectors.toList());
    return new BlockPosition(Stream.iterate(0, a -> a + 1).limit(this.payload.size())
            .map(a -> this.payload.get(a) + target.get(a))
            .collect(Collectors.toList()));
  }
}
