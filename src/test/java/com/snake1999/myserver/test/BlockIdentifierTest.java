package com.snake1999.myserver.test;

import com.snake1999.myserver.BlockIdentifier;

/**
 * By lmlstarqaq http://snake1999.com/
 * Creation time: 2017/5/19 13:08.
 */
public class BlockIdentifierTest {
    public static void main(String[] args) {
        BlockIdentifier bid = BlockIdentifier.ofStringId("my_category:my_block");
        BlockIdentifier bid2 = BlockIdentifier.ofStringId("my_category:my_block");
        BlockIdentifier bid3 = BlockIdentifier.ofStringId("my_category:my_block_2");

        assert bid.hashCode() == -777400477;
        assert BlockIdentifier.equals(bid, bid2);
        assert !bid3.equals(bid);
        assert bid2.getStringId().equals("my_category:my_block");
        assert bid.toString().equals("BlockIdentifier[my_category:my_block]");
    }
}
