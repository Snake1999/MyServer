package com.snake1999.myserver.core;

import java.util.Optional;
import java.util.function.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Wrapper for Stream to operate blocks easier.
 *
 * Copyright 2017 lmlstarqaq
 * All rights reserved.
 */
public interface BlockStream {

  BlockStream filter(Predicate<? super BlockLocated> predicate);

  BlockStream limit(BlockRegion region);

  BlockStream exclude(BlockRegion region);

  BlockStream peek(Consumer<? extends BlockLocated> consumer);

  BlockStream map(Function<? super BlockLocated, ? extends BlockExact> mapper);

  <R> Stream<R> mapToObj(Function<? super BlockLocated, ? extends R> mapper);

  IntStream mapToInt(ToIntFunction<? super BlockLocated> mapper);

  LongStream mapToLong(ToLongFunction<? super BlockLocated> mapper);

  DoubleStream mapToDouble(ToDoubleFunction<? super BlockLocated> mapper);

  Stream<BlockLocated> line();

  <R> Stream<R> lineToObj(Function<? super BlockLocated, ? extends R> mapper);

  IntStream lineToInt(ToIntFunction<? super BlockLocated> mapper);

  LongStream lineToLong(ToLongFunction<? super BlockLocated> mapper);

  DoubleStream lineToDouble(ToDoubleFunction<? super BlockLocated> mapper);

  BlockStream flatMap(Function<? super BlockLocated, ? extends BlockStream> mapper);

  <R> Stream<R> flatMapToObj(Function<? super BlockLocated, ? extends Stream<? extends R>> mapper);

  IntStream flatMapToInt(Function<? super BlockLocated, ? extends IntStream> mapper);

  LongStream flatMapToLong(Function<? super BlockLocated, ? extends LongStream> mapper);

  DoubleStream flatMapToDouble(Function<? super BlockLocated, ? extends DoubleStream> mapper);

  void forEach(Consumer<? super BlockLocated> action);

  long count();

  Optional<BlockLocated> findAny();


}
