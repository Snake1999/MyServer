package com.snake1999.myserver.core;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A collection that stores blocks.
 *
 * Block at one position can be undefined, or defined blocks.
 * Value 'null' represents undefined. Block air belongs to defined block.
 *
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/6/4 17:42.
 */
public interface BlockCollection {

  int size();

  boolean isEmpty();

  Optional<BlockExact> get(BlockPosition position);

  void put(BlockPosition position, BlockExact block);

  void clear();

  void remove(BlockPosition position);

  default void putAll(BlockCollection blockCollection) {
    blockCollection.forEach(l -> this.put(l.blockPosition(), l.blockExact()));
  }

  Optional<BlockExact> replace(BlockPosition position, BlockExact block);

  boolean replace(BlockPosition position, BlockExact oldBlock, BlockExact newBlock);

  BlockStream blockStream();

  boolean equals(Object o);

  int hashCode();

  void forEach(Consumer<BlockLocated> consumer);

}
