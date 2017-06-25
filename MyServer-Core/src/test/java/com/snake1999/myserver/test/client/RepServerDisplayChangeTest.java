package com.snake1999.myserver.test.client;

import com.snake1999.myserver.client.RepServerDisplayChange;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RepServerDisplayChange")
class RepServerDisplayChangeTest {

  private static RepServerDisplayChange rep;

  @BeforeAll
  static void buildUp() {
    rep = RepServerDisplayChange.newBuilder()
            .messageOfToday("Hello World")
            .playerCount(233)
            .playerLimit(123456)
            .build();
  }

  @Test
  @DisplayName("getter")
  void testGetter() {
    assertAll(
            () -> assertEquals(rep.getMessageOfToday(), "Hello World"),
            () -> assertEquals(rep.getPlayerCount(), 233),
            () -> assertEquals(rep.getPlayerLimit(), 123456)
    );
  }


}
