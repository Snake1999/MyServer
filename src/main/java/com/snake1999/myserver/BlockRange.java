package com.snake1999.myserver;

import java.util.*;

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
        return new BlockRange(Collections.emptySet(), Collections.emptySet(), true);
    }

    public static BlockRange empty() {
        return new BlockRange(Collections.emptySet(), Collections.emptySet(), false);
    }

    public static BlockRange rangeNot(BlockRange range) {
        return new BlockRange(range.excepts, range.takes, !range.takeInfinite); // TODO: 2017/5/20 check
    }
//
//    public static BlockRange rangeAnd(BlockRange... ranges) {}
//
//    public static BlockRange rangeOr(BlockRange... ranges) {}
//
//    public static BlockRange rangeXor(BlockRange... ranges) {}

    public boolean isEmpty() {
        return isEmpty0();
    }

    public boolean contains(BlockPosition position) {
        return contains0(position);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Override
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Internal
    ///////////////////////////////////////////////////////////////////////////

    private Set<CubeBR> takes = new HashSet<>();
    private Set<CubeBR> excepts = new HashSet<>();
    private boolean takeInfinite;

    private BlockRange(Set<CubeBR> takes, Set<CubeBR> excepts, boolean takeInfinite) {
        this.takes.addAll(takes);
        this.excepts.addAll(excepts);
        this.takeInfinite = takeInfinite;
    }

    private boolean contains0(BlockPosition position) {
        if(!takeInfinite)
            return takes.stream().anyMatch(c -> c.contains(position))
                    && excepts.stream().noneMatch(c -> c.contains(position));
        else return excepts.stream().noneMatch(c -> c.contains(position));
    }

    private boolean isEmpty0() {
        return takes.size() > 0; // TODO: 2017/5/20 OPERATION AND
    }

    private static class CubeBR {
        int[] payload = new int[6]; //x1, x2, y1, y2, z1, z2
        private CubeBR(int[] payload){
            System.arraycopy(payload, 0, payload, 0, 6);
            for (int i = 0; i <= 2; i++) {
                int p1 = 2 * i, p2 = 2 * i + 1;
                int min = Math.min(payload[p1], payload[p2]);
                int max = Math.max(payload[p1], payload[p2]);
                payload[p1] = min; payload[p2] = max;
            }
        }
        private boolean contains(BlockPosition position) {
            for (int i = 0; i <= 2; i++)
                if (position.payload[i] >= payload[2 * i] &&
                        position.payload[i] <= payload[2 * i + 1])
                    return true;
            return false;
        }
        private boolean isEmpty() {
            return false; // TODO: 2017/5/20
        }
    }

}
