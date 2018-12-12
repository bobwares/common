package com.bobwares.common.registry.exception;


public class ResourceReaderException extends RuntimeException {

  private static final String MESSAGE = "Error parsing resource.";

  public ResourceReaderException() {
    super(MESSAGE);
  }

  public ResourceReaderException(String message) {
    super(message);
  }

  public ResourceReaderException(String message, Throwable cause) {
    super(message, cause);
  }

}
