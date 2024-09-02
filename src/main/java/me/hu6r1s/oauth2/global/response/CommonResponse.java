package me.hu6r1s.oauth2.global.response;

public enum CommonResponse {
  SUCCESS("SU", "Success."),

  VALIDATION_FAIL("VF", "Validation failed."),
  DUPLICATE_ID("DI", "Duplicate id."),

  SIGN_IN_FAIL("SF", "Login information mismatch."),
  CERTIFICATION_FAIL("CF", "Certification failed."),

  DATABASE_ERROR("DBE", "Database error.");

  private final String code;
  private final String message;

  private CommonResponse(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
