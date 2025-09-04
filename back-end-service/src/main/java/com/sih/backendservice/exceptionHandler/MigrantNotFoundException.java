package com.sih.backendservice.exceptionHandler;

public class MigrantNotFoundException extends RuntimeException {
  public MigrantNotFoundException(String message) {
    super(message);
  }
}
