package me.hu6r1s.oauth2.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.hu6r1s.oauth2.dto.request.CheckCertificationRequestDto;
import me.hu6r1s.oauth2.dto.request.IdCheckRequestDto;
import me.hu6r1s.oauth2.dto.request.MailCertificationRequestDto;
import me.hu6r1s.oauth2.dto.response.CertificationResponseDto;
import me.hu6r1s.oauth2.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/id-check")
  public ResponseEntity<CertificationResponseDto> idCheck(
      @RequestBody @Valid IdCheckRequestDto requestDto
  ) {
    authService.idCheck(requestDto);
    return CertificationResponseDto.success();
  }

  @PostMapping("/email-certification")
  public ResponseEntity<CertificationResponseDto> mailCertification(
    @RequestBody @Valid MailCertificationRequestDto requestDto
  ) {
    authService.mailCertification(requestDto);
    return CertificationResponseDto.success();
  }

  @PostMapping("/check-certification")
  public ResponseEntity<CertificationResponseDto> checkCertification(
    @RequestBody @Valid CheckCertificationRequestDto requestDto
  ) {
    authService.checkCertification(requestDto);
    return CertificationResponseDto.success();
  }
}
