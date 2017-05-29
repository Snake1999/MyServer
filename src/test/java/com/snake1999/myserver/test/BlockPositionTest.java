package com.snake1999.myserver.test;

import com.snake1999.myserver.BlockPosition;

/**
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/5/19 17:37.
 */
public class BlockPositionTest {
    public static void main(String[] args) {
        BlockPosition p1, p2, p3;
        p1 = BlockPosition.of(-10, 10, 20);
        p2 = BlockPosition.of(-11, 9, 18).addXYZ(1, 1, 2);
        p3 = BlockPosition.of(10, 5, 6).addXYZ(0, 0, 1);

        assert p1.equals(p2);
        assert !BlockPosition.equals(p2, p3);
        assert p1.toString().equals("BlockPosition[x=-10, y=10, z=20]");
        assert p2.hashCode() == -24;
        assert String.format("x=%d, y=%d, z=%d", p3.getBlockX(), p3.getBlockY(), p3.getBlockZ())
                .equals("x=10, y=5, z=7");
    }
}
