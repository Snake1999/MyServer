package com.snake1999.myserver.core;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Copyright 2017 lmlstarqaq
 * All rights reserved.
 */
public interface BlockStream {

  BlockStream limit(BlockRegion region);

  BlockStream map(Function<? extends BlockLocated, ? extends BlockExact> mapper);

  <R> Stream<R> line(Function<? extends BlockLocated, ? extends R> mapper);

  //lineToLong...

  <R> Stream<R> flatMap(Function<? extends BlockLocated, ? extends Stream<? extends R>> mapper);

  //flatMapToLong...
}
