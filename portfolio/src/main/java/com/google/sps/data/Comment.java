package com.google.sps.data;

/** An item on a todo list. */
public final class Comment {

  private final long id;
  private final String username;
  private final long timestamp;
  private final String comment;

  public Comment(long id, long timestamp, String username, String comment) {
    this.id = id;
    this.timestamp = timestamp;
    this.username = username;
    this.comment = comment;
  }
}