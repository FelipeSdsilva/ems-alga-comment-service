package com.felipesouls.alga_comment_service.api.controllers.exceptions;


import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.nio.channels.ClosedChannelException;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler({
      SocketTimeoutException.class,
      ConnectException.class,
      ClosedChannelException.class
  })
  public ProblemDetail handle(IOException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.GATEWAY_TIMEOUT);
    problemDetail.setTitle("Bad Gateway");
    problemDetail.setDetail(e.getMessage());
    problemDetail.setType(URI.create("/errors/gateway-timeout"));
    return problemDetail;
  }

  @ExceptionHandler(ModerationClientBadGatewayException.class)
  public ProblemDetail handle(ModerationClientBadGatewayException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_GATEWAY);
    problemDetail.setTitle("Bad Gateway");
    problemDetail.setDetail(e.getMessage());
    problemDetail.setType(URI.create("/errors/bad-gateway"));
    return problemDetail;
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ProblemDetail handleResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    problemDetail.setTitle("Resource Not Found");
    problemDetail.setDetail(e.getMessage());
    problemDetail.setType(URI.create(request.getRequestURL().toString()));
    return problemDetail;
  }

  @ExceptionHandler(CommentValidationException.class)
  public ProblemDetail handle(CommentValidationException e, HttpServletRequest request) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
    problemDetail.setTitle("Unprocessable Entity");
    problemDetail.setDetail(e.getMessage());
    problemDetail.setType(URI.create(request.getRequestURL().toString()));
    return problemDetail;
  }
}
