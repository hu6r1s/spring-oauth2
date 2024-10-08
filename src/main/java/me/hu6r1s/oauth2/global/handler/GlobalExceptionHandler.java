package me.hu6r1s.oauth2.global.handler;

import me.hu6r1s.oauth2.dto.response.CertificationResponseDto;
import me.hu6r1s.oauth2.global.response.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
  public ResponseEntity<CertificationResponseDto> validationExceptionHandler(Exception e) {
    CertificationResponseDto response = new CertificationResponseDto(
        ResponseCode.VALIDATION_FAIL.getCode(), ResponseCode.VALIDATION_FAIL.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(CustomException.class)
  public static ResponseEntity<CertificationResponseDto> customExceptionHandler(CustomException e) {
    CertificationResponseDto response = new CertificationResponseDto(
        e.getCode(), e.getMessage()
    );
    return ResponseEntity.status(e.getStatus()).body(response);
  }
}
