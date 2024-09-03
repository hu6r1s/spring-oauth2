package me.hu6r1s.oauth2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.hu6r1s.oauth2.global.response.ResponseCode;

@Getter
@AllArgsConstructor
public class CertificationResponseDto {

  private String code;
  private String message;

  public CertificationResponseDto() {
    this.code = ResponseCode.SUCCESS.getCode();
    this.message = ResponseCode.SUCCESS.getMessage();
  }
}
