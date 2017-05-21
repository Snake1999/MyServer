package com.snake1999.myserver;

import java.util.*;

import static com.snake1999.myserver.Dimensions.DIMENSION_COUNT;
import static com.snake1999.myserver.Dimensions.dimensions;

/**
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/5/20 12:06.
 */
public final class BlockRange {

//    public static BlockRange cube(BlockPosition... contained) {
//        if(contained.length == 0) return empty();
//
//    }
//
//    public static BlockRange align(int x1, int x2, int y1, int y2, int z1, int z2) {
//
//    }
//
    public static BlockRange infinite() {
        return new BlockRange(BitSet.valueOf(new long[]{0b1L}), emptySliceSet());
    }

    public static BlockRange empty() {
        return new BlockRange(BitSet.valueOf(new long[]{0b0L}), emptySliceSet());
    }

//    public static BlockRange logicNot(BlockRange range) {}
//
//    public static BlockRange logicAnd(BlockRange... ranges) {}
//
//    public static BlockRange logicOr(BlockRange... ranges) {}
//
//    public static BlockRange logicXor(BlockRange... ranges) {}


    ///////////////////////////////////////////////////////////////////////////
    // Override
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Internal
    ///////////////////////////////////////////////////////////////////////////

    // boolean payload[d0 + d1*size0 + d2*size1*size0] taken(d0, d1, d2);
    private BitSet payload;
    // e.g.: slices[0] = {10, 20}, divides the space into 3 parts (-inf, 10], (10, 20], (20, inf)
    private Set<Integer>[] slices;

    private static Set<Integer>[] emptySliceSet() {
        @SuppressWarnings("unchecked")
        Set<Integer>[] ans = (Set<Integer>[]) new Set[DIMENSION_COUNT];
        Arrays.fill(ans, Collections.emptySet());
        return ans;
    }

    private BlockRange(BitSet payload, Set<Integer>[] slices) {
        this.payload = payload;
        this.slices = slices;
    }

//    private boolean contains(BlockPosition position) {
//        dimensions().forEach(i -> );
//    }

//    private int indexOfPayload(BlockPosition position) {
//        dimensions().forEach(i -> {
//            // slices[i] => {10, 20} => (-inf, 10], (10, 20], (20, inf)
//            // position.payload[i] => 10/11/20/30
//            // findFirst => absent/
//            slices[i].stream().sorted(Integer::compareTo)
//                    .filter(d -> position.payload[i] > d)
//                    .findFirst()
//        });
//    }

}
