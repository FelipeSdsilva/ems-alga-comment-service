package com.felipesouls.alga_comment_service.api.controllers.exceptions;

public class ModerationClientBadGatewayException extends RuntimeException {
  public ModerationClientBadGatewayException(String message) {
    super(message);
  }
}
