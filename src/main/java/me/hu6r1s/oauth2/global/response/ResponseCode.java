package me.hu6r1s.oauth2.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {
  SUCCESS("SU", HttpStatus.OK, "Success."),

  VALIDATION_FAIL("VF", HttpStatus.BAD_REQUEST, "Validation failed."),
  DUPLICATE_ID("DI", HttpStatus.BAD_REQUEST, "Duplicate id."),

  SIGN_IN_FAIL("SF", HttpStatus.UNAUTHORIZED, "Login information mismatch."),
  CERTIFICATION_FAIL("CF", HttpStatus.UNAUTHORIZED, "Certification failed."),

  DATABASE_ERROR("DBE", HttpStatus.INTERNAL_SERVER_ERROR, "Database error."),
  MAIL_FAIL("MF", HttpStatus.INTERNAL_SERVER_ERROR, "Mail send failed.");

  private final String code;
  private final HttpStatus status;
  private final String message;

  ResponseCode(String code, HttpStatus status, String message) {
    this.code = code;
    this.status = status;
    this.message = message;
  }
}
