package com.snake1999.myserver.core;

import java.util.Objects;

import static com.snake1999.myserver.core.Messages.*;

/**
 * BlockLocated is BlockExact with position data.
 *
 * Copyright 2017 lmlstarqaq
 * All rights reserved.
 */
public final class BlockLocated {

  public static BlockLocated of(BlockExact blockExact, BlockPosition blockPosition) {
    Objects.requireNonNull(blockExact, block_exact_can_not_be_null);
    Objects.requireNonNull(blockPosition, block_position_can_not_be_null);
    return new BlockLocated(blockExact, blockPosition);
  }

  public BlockExact blockExact() {
    return blockExact;
  }

  public BlockPosition blockPosition() {
    return blockPosition;
  }

  ///////////////////////////////////////////////////////////////////////////
  // Override
  ///////////////////////////////////////////////////////////////////////////

  @Override
  public boolean equals(Object obj) {
    return obj instanceof BlockLocated &&
            Objects.equals(((BlockLocated) obj).blockExact(), this.blockExact()) &&
            Objects.equals(((BlockLocated) obj).blockPosition(), this.blockPosition());
  }

  @Override
  public int hashCode() {
    return blockExact().hashCode() ^ blockPosition().hashCode();
  }

  @Override
  public String toString() {
    return String.format("BlockLocated{blockExact = %s, blockPosition = %s}",
            blockExact().toString(), blockPosition().toString());
  }

  ///////////////////////////////////////////////////////////////////////////
  // Internal
  ///////////////////////////////////////////////////////////////////////////

  private final BlockExact blockExact;
  private final BlockPosition blockPosition;

  private BlockLocated(BlockExact blockExact, BlockPosition blockPosition) {
    this.blockExact = blockExact;
    this.blockPosition = blockPosition;
  }


}
