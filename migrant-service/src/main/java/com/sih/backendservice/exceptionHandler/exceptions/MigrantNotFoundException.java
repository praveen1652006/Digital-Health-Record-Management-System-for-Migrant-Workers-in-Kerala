package com.sih.backendservice.exceptionHandler.exceptions;

public class MigrantNotFoundException extends RuntimeException {
  public MigrantNotFoundException(String message) {
    super(message);
  }
}
