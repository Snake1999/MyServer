package com.snake1999.myserver.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.Consumer;

/**
 * A mutable thread-unsafe block list using array.
 *
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/6/6 22:13.
 */
public class ArrayBlockList implements BlockCollection {

  private Map<ChunkPos, Chunk> posToChunk;
  private Map<Integer, String> id;
  private Map<BlockPosition, BlockAttachment> special;

  private static class ChunkPos{
    private int chunkX, chunkZ; // min{blockX} = chunkX * 16, etc
    private ChunkPos(int chunkX, int chunkZ) {
      this.chunkX = chunkX;
      this.chunkZ = chunkZ;
    }
    static ChunkPos from(BlockPosition p) {
      return new ChunkPos(p.getBlockX() << 4, p.getBlockZ() << 4);
    }
    @Override public boolean equals(Object obj) {
      return obj instanceof ChunkPos && this.chunkX == ((ChunkPos) obj).chunkX && this.chunkZ == ((ChunkPos) obj).chunkZ;
    }
    @Override public int hashCode() {return chunkX * 65536 + chunkZ;}
  }

  private static int chunkSize0 = 16 * 256 * 16;
  private static AtomicIntegerArray emptyBuffer0 = new AtomicIntegerArray(chunkSize0);

  // A chunk is a holder for block sized 16*256*16.
  private static class Chunk {
    private AtomicIntegerArray buffer;
    Chunk() {
      buffer = new AtomicIntegerArray(chunkSize0);
    }
  }

  private static int indexOfBuffer(BlockPosition bp) {
    int bX = bp.getBlockX(), bY = bp.getBlockY(), bZ = bp.getBlockZ();
    bX %= 16; bY %= 256; bZ %= 16;
    return bX + bY * 16 + bZ * 16 * 256;
  }

  private ChunkPos ensure0(BlockPosition bp) {
    ChunkPos chunkPos = ChunkPos.from(bp);
    posToChunk.computeIfAbsent(chunkPos, pp -> new Chunk());
    return chunkPos;
  }

  private Optional<String> getString0(BlockPosition bp) {
    ChunkPos cp = ensure0(bp);
    int intId = posToChunk.get(cp).buffer.get(indexOfBuffer(bp));
    return Optional.ofNullable(id.getOrDefault(intId, null));
  }

  private int nextFreeInt() {
    return id.size();
  }

  private void put0(BlockPosition bp, BlockAttachment a, BlockIdentifier i) {
    int intId = nextFreeInt();
    id.put(intId, i.stringId());
    special.put(bp, a);
    ChunkPos chunkPos = ensure0(bp);
    posToChunk.get(chunkPos).buffer.set(indexOfBuffer(bp), intId);
  }

  private static int intIdForBlockAbsent = 0;

  private void reset0(){
    posToChunk = new HashMap<>();
    id = new HashMap<>();
    special = new HashMap<>();
    id.put(intIdForBlockAbsent, ""); // for absent block
  }

  private void checkChunk0(ChunkPos cp) {
    if(!posToChunk.containsKey(cp)) return;
    if(!Objects.equals(posToChunk.get(cp).buffer, emptyBuffer0)) return;
    posToChunk.remove(cp);
  }

  private void delete0(BlockPosition bp) {
    ChunkPos cp = ChunkPos.from(bp);
    if(!posToChunk.containsKey(cp)) return;
    posToChunk.get(cp).buffer.set(indexOfBuffer(bp), intIdForBlockAbsent);
    checkChunk0(cp);
  }

  public ArrayBlockList() {
    reset0();
  }

  @Override
  public int size() {
    return 16 * 256 * 16 * posToChunk.size();
  }

  @Override
  public boolean isEmpty() {
    return posToChunk.isEmpty();
  }

  @Override
  public Optional<BlockExact> get(BlockPosition position) {
    return getString0(position).map(BlockIdentifier::ofStringId)
            .map(i -> BlockExact.of(i, special.getOrDefault(position, null)));
  }

  @Override
  public void put(BlockPosition position, BlockExact block) {
    put0(position, block.blockAttachment(), block.blockIdentifier());
  }

  @Override
  public void clear() {
    reset0();
  }

  @Override
  public void remove(BlockPosition position) {
    delete0(position);
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
