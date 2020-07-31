package com.google.sps.data;

/** An item on a todo list. */
public final class UserDetail {

  private final String userEmail;
  private final String state;
  private final String logUrl;

  public UserDetail(String userEmail, String state, String logUrl) {
    this.userEmail = userEmail;
    this.state = state;
    this.logUrl = logUrl;
  }
}