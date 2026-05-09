package com.felipesouls.alga_comment_service.api.controllers.exceptions;

public class CommentValidationException extends RuntimeException {
  public CommentValidationException(String message) {
    super(message != null ? message : "Comment not approved");
  }
}
