package com.snake1999.myserver.test;

import java.util.BitSet;

/**
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/5/20 12:17.
 */
public final class BlockRangeTest {
    public static void main(String[] args) {
        BitSet a = new BitSet(24);
        a.set(15);
        System.out.println(a.get(16));
        System.out.println(bitSetToString(a));
    }

    private static String bitSetToString(BitSet bs) {
        int len = bs.length();
        StringBuilder buf = new StringBuilder(len);
        for(int i=0; i<len; i++) {
            buf.append(bs.get(i) ? '1' : '0');
            if(i%8==0) buf.append(" ");
        }
        return buf.toString();
    }
}
