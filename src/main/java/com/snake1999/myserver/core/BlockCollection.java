package com.snake1999.myserver.core;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/6/4 17:42.
 */
public interface BlockCollection {

  int size();

  boolean isEmpty();

  BlockRegion definition();

  Optional<BlockExact> get(BlockPosition position);

  void put(BlockPosition position, BlockExact block);

  void putIfAbsent(BlockPosition position, BlockExact block);

  void clear();

  void remove(BlockPosition position);

  void putAll(BlockCollection blockCollection);

  BlockExact replace(BlockPosition position, BlockExact block);

  boolean replace(BlockPosition position, BlockExact oldBlock, BlockExact newBlock);

  void merge(BlockCollection other,
             BiFunction<? extends BlockExact, ? extends BlockExact, ? extends BlockExact> remapping);

  void computeIfAbsent(BlockPosition position,
                       Function<? extends BlockPosition, ? extends BlockExact> remapping);

  void computeIfPresent(BlockPosition position,
                        BiFunction<? extends BlockPosition, ? extends BlockExact, ? extends BlockExact> remapping);

  void compute(BlockPosition position,
               BiFunction<? extends BlockPosition, ? extends BlockExact, ? extends BlockExact> remapping);

  BlockStream blockStream();

  boolean equals(Object o);

  int hashCode();

}
