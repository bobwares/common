package com.bobwares.common.mapper;


public class ObjectMappingException extends RuntimeException {

  private static final String MESSAGE = "Unable to convert request to jsonNode object.";

  public ObjectMappingException() {
    super(MESSAGE);
  }

  public ObjectMappingException(String message) {
    super(message);
  }

  public ObjectMappingException(String message, Throwable cause) {
    super(message, cause);
  }

}
