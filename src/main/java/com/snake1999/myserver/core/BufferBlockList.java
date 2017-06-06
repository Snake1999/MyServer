package com.snake1999.myserver.core;

import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A mutable thread-unsafe block list using heap buffer.
 *
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/6/6 22:13.
 */
public class BufferBlockList implements BlockCollection {

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

  // A chunk is a holder for block sized 16*256*16.
  private static class Chunk {
    private IntBuffer buffer;
    Chunk() {
      buffer = IntBuffer.allocate(16 * 256 * 16);
    }
  }

  private static int indexOfBuffer(BlockPosition position) {
    int bX = position.getBlockX(), bY = position.getBlockY(), bZ = position.getBlockZ();
    bX %= 16; bY %= 256; bZ %= 16;
    return bX + bY * 16 + bZ * 16 * 256;
  }

  private ChunkPos ensure0(BlockPosition p) {
    ChunkPos chunkPos = ChunkPos.from(p);
    posToChunk.computeIfAbsent(chunkPos, pp -> new Chunk());
    return chunkPos;

  }

  private Optional<String> getString0(BlockPosition pos) {
    ChunkPos chunkPos = ensure0(pos);
    int intId = posToChunk.get(chunkPos).buffer.get(indexOfBuffer(pos));
    return Optional.ofNullable(id.getOrDefault(intId, null));
  }

  private int nextFreeInt() {
    return id.size();
  }

  private void put0(BlockPosition pos, BlockAttachment a, BlockIdentifier i) {
    int intId = nextFreeInt();
    id.put(intId, i.stringId());
    special.put(pos, a);
    ChunkPos chunkPos = ensure0(pos);
    posToChunk.get(chunkPos).buffer.put(indexOfBuffer(pos), intId);
  }

  private void reset0(){
    posToChunk = new HashMap<>();
    id = new HashMap<>();
    special = new HashMap<>();
  }

  public BufferBlockList() {
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
