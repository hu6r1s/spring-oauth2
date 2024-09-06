package me.hu6r1s.oauth2.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.hu6r1s.oauth2.dto.request.IdCheckRequestDto;
import me.hu6r1s.oauth2.dto.response.IdCheckResponseDto;
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
  public ResponseEntity<IdCheckResponseDto> idCheck(
      @RequestBody @Valid IdCheckRequestDto requestDto
  ) {
    authService.idCheck(requestDto);
    return IdCheckResponseDto.success();
  }
}
