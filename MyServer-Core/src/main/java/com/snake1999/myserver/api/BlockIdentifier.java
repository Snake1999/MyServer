package com.snake1999.myserver.api;

import java.util.Objects;

import static com.snake1999.myserver.api.API.Definition.UNIVERSAL;
import static com.snake1999.myserver.api.API.Usage.BLEEDING;
import static com.snake1999.myserver.api.Messages.*;

/**
 * BlockIdentifier identifies the block. It only stores string id (=int id and meta), excluding extra data (NBT).
 * Custom blocks with custom string id is also accepted.
 *
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/5/19 13:03.
 */
@API(usage = BLEEDING, definition = UNIVERSAL)
public final class BlockIdentifier {

  @API(usage = BLEEDING, definition = UNIVERSAL)
  public static BlockIdentifier of(String stringId, int integerMeta) {
    Objects.requireNonNull(stringId, string_id_can_not_be_null);
    return new BlockIdentifier(stringId, integerMeta);
  }

  @API(usage = BLEEDING, definition = UNIVERSAL)
  public static boolean equals(BlockIdentifier a, BlockIdentifier b) {
    Objects.requireNonNull(a, block_identifier_can_not_be_null);
    Objects.requireNonNull(b, block_identifier_can_not_be_null);
    return Objects.equals(a.stringId, b.stringId) && Objects.equals(a.integerMeta, b.integerMeta);
  }

  @API(usage = BLEEDING, definition = UNIVERSAL)
  public String stringId() {
    return stringId;
  }

  @API(usage = BLEEDING, definition = UNIVERSAL)
  public int integerMeta(){
    return integerMeta;
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
    return stringId.hashCode()^Integer.hashCode(integerMeta);
  }

  @Override
  public String toString() {
    return String.format("BlockIdentifier[%s:%d]", stringId, integerMeta);
  }

  ///////////////////////////////////////////////////////////////////////////
  // Internal
  ///////////////////////////////////////////////////////////////////////////

  private BlockIdentifier(String stringId, int integerMeta) {
    this.stringId = stringId;
    this.integerMeta = integerMeta;
  }

  private String stringId;
  private int integerMeta;

}
