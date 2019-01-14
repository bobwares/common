package com.bobwares.common.converter;


public class ObjectConverterException extends RuntimeException {

  private static final String MESSAGE = "Unable to convert request to jsonNode object.";

  public ObjectConverterException() {
    super(MESSAGE);
  }

  public ObjectConverterException(String message) {
    super(message);
  }

  public ObjectConverterException(String message, Throwable cause) {
    super(message, cause);
  }

}
