package com.snake1999.myserver;

import java.util.Objects;

/**
 * Immutable class declaring positions of blocks.
 *
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/5/19 13:00.
 */
public final class BlockPosition {

    public static BlockPosition of(int blockX, int blockY, int blockZ) {
        return new BlockPosition(blockX, blockY, blockZ);
    }

    public static boolean equals(BlockPosition a, BlockPosition b) {
        Objects.requireNonNull(a, Messages.block_position_can_not_be_null);
        Objects.requireNonNull(b, Messages.block_position_can_not_be_null);
        return a.blockX == b.blockX && a.blockY == b.blockY && a.blockZ == b.blockZ;
    }

    public int getBlockX() {
        return blockX;
    }

    public int getBlockY() {
        return blockY;
    }

    public int getBlockZ() {
        return blockZ;
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
        return Integer.hashCode(blockX) ^ Integer.hashCode(blockY) ^ Integer.hashCode(blockZ);
    }

    @Override
    public String toString() {
        return String.format("BlockPosition[x=%d, y=%d, z=%d]", blockX, blockY, blockZ);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Internal
    ///////////////////////////////////////////////////////////////////////////

    private int blockX, blockY, blockZ;

    private BlockPosition(int blockX, int blockY, int blockZ) {
        this.blockX = blockX;
        this.blockY = blockY;
        this.blockZ = blockZ;
    }
}
