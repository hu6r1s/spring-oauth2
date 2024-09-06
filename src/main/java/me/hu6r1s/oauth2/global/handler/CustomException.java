package me.hu6r1s.oauth2.global.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

  private String code;
  private HttpStatus status;
  public CustomException(String code, HttpStatus status, String message) {
    super(message);
    this.code = code;
    this.status = status;
  }
}
