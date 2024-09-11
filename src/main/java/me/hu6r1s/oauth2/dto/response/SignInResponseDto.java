package me.hu6r1s.oauth2.dto.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignInResponseDto extends CertificationResponseDto {

  private String token;
  private int expirationTime;

  public SignInResponseDto(String token) {
    super();
    this.token = token;
    this.expirationTime = 3600;
  }

  public static ResponseEntity<SignInResponseDto> success(String token) {
    SignInResponseDto response = new SignInResponseDto(token);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
