package com.snake1999.myserver;

import java.util.Arrays;
import java.util.Objects;

import static com.snake1999.myserver.Dimensions.DIMENSION_COUNT;

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
        return Arrays.equals(a.payload, b.payload);
    }

    public int getBlockX() {
        return payload[0];
    }

    public int getBlockY() {
        return payload[1];
    }

    public int getBlockZ() {
        return payload[2];
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
        return Integer.hashCode(getBlockX()) ^ Integer.hashCode(getBlockY()) ^ Integer.hashCode(getBlockZ());
    }

    @Override
    public String toString() {
        return String.format("BlockPosition[x=%d, y=%d, z=%d]", getBlockX(), getBlockY(), getBlockZ());
    }

    ///////////////////////////////////////////////////////////////////////////
    // Internal
    ///////////////////////////////////////////////////////////////////////////

    int[] payload = new int[DIMENSION_COUNT]; //blockX, blockY, blockZ

    private BlockPosition(int... payload) {
        System.arraycopy(payload, 0, this.payload, 0, DIMENSION_COUNT);
    }
}
