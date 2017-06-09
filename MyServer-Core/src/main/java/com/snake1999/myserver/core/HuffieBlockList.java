package com.snake1999.myserver.core;

import java.util.BitSet;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * A block list using uncompressed Huffman Tree.
 * HuffieBlockList is very fast on replacing, and has same speed on getting and putting with memory tables.
 *
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/6/6 22:08.
 */
public class HuffieBlockList implements BlockCollection {

  private BitSet buffer;
  private Map<BitSet, String> mapper;
  private Map<BlockPosition, Integer> index;

  @Override
  public int size() {
    return 0;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public Optional<BlockExact> get(BlockPosition position) {
    return null;
  }

  @Override
  public void put(BlockPosition position, BlockExact block) {

  }

  @Override
  public void clear() {

  }

  @Override
  public void remove(BlockPosition position) {

  }

  @Override
  public void putAll(BlockCollection blockCollection) {

  }

  @Override
  public Optional<BlockExact> replace(BlockPosition position, BlockExact block) {
    return null;
  }

  @Override
  public boolean replace(BlockPosition position, BlockExact oldBlock, BlockExact newBlock) {
    return false;
  }

  @Override
  public BlockStream blockStream() {
    return null;
  }

  @Override
  public void forEach(Consumer<BlockLocated> consumer) {

  }
}
