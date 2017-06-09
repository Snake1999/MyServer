package com.snake1999.myserver.core;

import java.util.Objects;

/**
 * BlockExact contains full block data, including BlockIdentifier and BlockAttachment.
 * For example, chests with items inside, skulls with skull type and skins.
 *
 * This is used for storing exact block data, e.g. block as item in inventory, or
 * as return value of block getter methods in BlockCollection.
 *
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/6/5 12:03.
 */
public final class BlockExact {

  public static BlockExact of(BlockIdentifier identifier, BlockAttachment attachment) {
    Objects.requireNonNull(identifier, Messages.block_identifier_can_not_be_null);
    Objects.requireNonNull(attachment, Messages.block_attachment_can_not_be_null);
    return new BlockExact(identifier, attachment);
  }

  public BlockIdentifier blockIdentifier() {
    return blockIdentifier;
  }

  public BlockAttachment blockAttachment() {
    return blockAttachment;
  }

  ///////////////////////////////////////////////////////////////////////////
  // Override
  ///////////////////////////////////////////////////////////////////////////

  @Override
  public boolean equals(Object obj) {
    return obj instanceof BlockExact &&
            Objects.equals(((BlockExact) obj).blockAttachment(), this.blockAttachment()) &&
            Objects.equals(((BlockExact) obj).blockIdentifier(), this.blockIdentifier());
  }

  @Override
  public int hashCode() {
    return blockAttachment().hashCode() ^ blockIdentifier().hashCode();
  }

  @Override
  public String toString() {
    return String.format("BlockExact{blockIdentifier = %s, blockAttachment = %s}",
            blockIdentifier().toString(), blockAttachment().toString());
  }

  ///////////////////////////////////////////////////////////////////////////
  // Internal
  ///////////////////////////////////////////////////////////////////////////

  private final BlockIdentifier blockIdentifier;
  private final BlockAttachment blockAttachment;

  private BlockExact(BlockIdentifier blockIdentifier, BlockAttachment blockAttachment) {
    this.blockIdentifier = blockIdentifier;
    this.blockAttachment = blockAttachment;
  }


}
