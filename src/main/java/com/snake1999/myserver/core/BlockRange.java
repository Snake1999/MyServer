package com.snake1999.myserver.core;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/5/20 12:06.
 */
public final class BlockRange {

  public static BlockRange expandedCubeContains(BlockPosition... contained) {
    if(contained.length == 0) return empty();
    List<Set<Integer>> slices = Dimensions.dimensions().mapToObj(i ->{
      IntSummaryStatistics s = Arrays.stream(contained).map(bp -> bp.payload.get(i)).mapToInt(ii -> ii).summaryStatistics();
      return new HashSet<Integer>() {{add(s.getMax() + 1); add(s.getMin());}};
    }).collect(Collectors.toList());
    BitSet payload = BitSet.valueOf(new long[]{0b000_010_000__000_000_000L});
    return new BlockRange(payload, slices);
  }
//
//    public static BlockRange align(int x1, int x2, int y1, int y2, int z1, int z2) {
//
//    }
//
  public static BlockRange infinite() {
    return new BlockRange(BitSet.valueOf(new long[]{0b1L}), emptySlices());
  }

  public static BlockRange empty() {
    return new BlockRange(BitSet.valueOf(new long[]{0b0L}), emptySlices());
  }

//  public static BlockRange logicNot(BlockRange range) {}

//    public static BlockRange logicAnd(BlockRange... ranges) {}
//
//    public static BlockRange logicOr(BlockRange... ranges) {}
//
//    public static BlockRange logicXor(BlockRange... ranges) {}

  public static boolean contains(BlockRange range, BlockPosition position) {
    return range.payload.get(indexOfPayload(position, range.slices));
  }


  ///////////////////////////////////////////////////////////////////////////
  // Override
  ///////////////////////////////////////////////////////////////////////////


  private static String bitSetToString(BitSet bs) {
    int len = bs.length();
    StringBuilder buf = new StringBuilder(len);
    for(int i=0; i<len; i++) {
      buf.append(bs.get(i) ? '1' : '0');
      if(i%8==0) buf.append(" ");
    }
    return buf.toString();
  }

  @Override
  public String toString() {
    return slices.toString() + "\npayload: " + bitSetToString(payload);
  }

  ///////////////////////////////////////////////////////////////////////////
  // Internal
  ///////////////////////////////////////////////////////////////////////////

  // boolean payload[d0 + d1*size0 + d2*size1*size0] taken(d0, d1, d2);
  private BitSet payload;
  // e.g.: slices[0] = {10, 20}, divides the space into 3 parts (-inf, 10), [10, 20), [20, inf)
  private List<Set<Integer>> slices;

  private static List<Set<Integer>> emptySlices() {

    return Stream.generate((Supplier<HashSet<Integer>>) HashSet::new).limit(Dimensions.DIMENSION_COUNT).collect(Collectors.toList());
  }

  private BlockRange(BitSet payload, List<Set<Integer>> slices) {
    this.payload = payload;
    this.slices = slices;
  }

  private BlockRange modifyWithInject(int dimension, int sliceAt) {
    slices.get(dimension).add(sliceAt);
    return this;
  }

  /*
   * slices[i] => {10, 20} =>
   * position.payload[i]  (-inf, 10), [10, 20), [20, inf)
   * value of s
   *              10      p<s         p>=s        p>=s
   *              20      p<s         p<s         p>=s
   *   count of 'p>=s'    0           1           2
   * define count[i] as count of 'p>=s'.
   * index = count[0] +
   *         count[1] * size[0] +
   *         count[2] * size[0] * size[1];
   *
   * * ensure no data flow *
   */
  private static int indexOfPayload(BlockPosition position, List<Set<Integer>> slices) {

//    System.out.println(dimensions().mapToObj(i -> slices.get(i).stream().filter(s -> position.payload[i] >= s).count())
//            .map(a -> a + ",").reduce(String::concat).orElse(""));
//    System.out.println(dimensions().mapToObj(i -> sizeOfDimension(i, slices))
//    .map(a -> a + "!").reduce(String::concat).orElse(""));

    //    System.out.println(ans);
    return (int) Dimensions.dimensions().mapToLong(i ->
            Math.multiplyExact(
                    slices.get(i).stream().filter(s -> position.payload.get(i) >= s).count(),
                    sizeOfDimension(i, slices)
            )
    ).sum();
  }

  private static long sizeOfDimension(int dimension, List<Set<Integer>> slices) {
    if(dimension == 0) return 1;
    else return Dimensions.dimensions().limit(dimension).map(d -> slices.get(d).size() + 1).asLongStream()
            .reduce(Math::multiplyExact).orElse(0);
  }

}
