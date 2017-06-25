package com.snake1999.myserver.client;

import com.snake1999.myserver.api.API;

import static com.snake1999.myserver.api.API.Definition.UNIVERSAL;
import static com.snake1999.myserver.api.API.Usage.BLEEDING;

@API(usage = BLEEDING, definition = UNIVERSAL)
public final class RepServerDisplayChange {

  @API(usage = BLEEDING, definition = UNIVERSAL)
  public String getMessageOfToday() {
    return messageOfToday;
  }

  @API(usage = BLEEDING, definition = UNIVERSAL)
  public int getPlayerCount() {
    return playerCount;
  }

  @API(usage = BLEEDING, definition = UNIVERSAL)
  public int getPlayerLimit() {
    return playerLimit;
  }

  @API(usage = BLEEDING, definition = UNIVERSAL)
  public static Builder newBuilder() {
    return new Builder();
  }

  @API(usage = BLEEDING, definition = UNIVERSAL)
  public static class Builder{

    @API(usage = BLEEDING, definition = UNIVERSAL)
    public Builder messageOfToday(String messageOfToday) {
      this.messageOfToday = messageOfToday;
      return this;
    }

    @API(usage = BLEEDING, definition = UNIVERSAL)
    public Builder playerCount(int playerCount) {
      this.playerCount = playerCount;
      return this;
    }

    @API(usage = BLEEDING, definition = UNIVERSAL)
    public Builder playerLimit(int playerLimit) {
      this.playerLimit = playerLimit;
      return this;
    }

    @API(usage = BLEEDING, definition = UNIVERSAL)
    public RepServerDisplayChange build() {
      return new RepServerDisplayChange(messageOfToday, playerCount, playerLimit);
    }

    private Builder() {}

    private String messageOfToday;
    private int playerCount;
    private int playerLimit;
  }

  ///////////////////////////////////////////////////////////////////////////
  // Internal
  ///////////////////////////////////////////////////////////////////////////

  private final String messageOfToday;
  private final int playerCount;
  private final int playerLimit;

  private RepServerDisplayChange(String messageOfToday, int playerCount, int playerLimit) {
    this.messageOfToday = messageOfToday;
    this.playerCount = playerCount;
    this.playerLimit = playerLimit;
  }

}
