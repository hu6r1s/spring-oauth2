package me.hu6r1s.oauth2.dto.response;

import lombok.Getter;
import me.hu6r1s.oauth2.global.response.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class IdCheckResponseDto extends CertificationResponseDto {

  private IdCheckResponseDto() {
    super();
  }

  public static ResponseEntity<IdCheckResponseDto> success() {
    IdCheckResponseDto response = new IdCheckResponseDto();
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
