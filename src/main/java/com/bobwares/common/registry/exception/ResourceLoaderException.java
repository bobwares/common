package com.bobwares.common.registry.exception;

public class ResourceLoaderException extends RuntimeException {

  private static final String MESSAGE = "Unable to convert request to jsonNode object.";

  public ResourceLoaderException() {
    super(MESSAGE);
  }

  public ResourceLoaderException(String message) {
    super(message);
  }

  public ResourceLoaderException(String message, Throwable cause) {
    super(message, cause);
  }

}
