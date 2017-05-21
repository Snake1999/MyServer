package com.snake1999.myserver;

import java.util.stream.IntStream;

/**
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/5/20 17:34.
 */
final class Dimensions {
    private Dimensions() {
        //no instance
    }

    static int DIMENSION_COUNT = 3;

    // 0, 1, 2
    static IntStream dimensions() {
        return IntStream.range(0, DIMENSION_COUNT);
    }
}
