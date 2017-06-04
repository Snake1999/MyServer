package com.snake1999.myserver.core;

import java.util.Objects;

/**
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/5/19 13:03.
 */
public class BlockIdentifier {

    public static BlockIdentifier ofStringId(String stringId) {
        return new BlockIdentifier(stringId);
    }

    public static boolean equals(BlockIdentifier a, BlockIdentifier b) {
        Objects.requireNonNull(a, Messages.block_identifier_can_not_be_null);
        Objects.requireNonNull(b, Messages.block_identifier_can_not_be_null);
        return Objects.equals(a.stringId, b.stringId);
    }

    public String getStringId() {
        return stringId;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Override
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BlockIdentifier && equals(this, (BlockIdentifier) obj);
    }

    @Override
    public int hashCode() {
        return stringId.hashCode();
    }

    @Override
    public String toString() {
        return String.format("BlockIdentifier[%s]", stringId);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Internal
    ///////////////////////////////////////////////////////////////////////////

    private BlockIdentifier(String stringId) {
        Objects.requireNonNull(stringId, Messages.string_id_can_not_be_null);
        this.stringId = stringId;
    }

    private String stringId;

}
