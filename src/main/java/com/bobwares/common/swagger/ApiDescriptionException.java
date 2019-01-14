package com.bobwares.common.swagger;

public class ApiDescriptionException extends RuntimeException {

  private static final String MESSAGE = "Unable to load Swagger API Info from apiInfo.md.";

  public ApiDescriptionException() {
    super(MESSAGE);
  }

  ApiDescriptionException(String message) {
    super(message);
  }

  public ApiDescriptionException(String message, Throwable cause) {
    super(message, cause);
  }
}