package me.hu6r1s.oauth2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.hu6r1s.oauth2.dto.request.CheckCertificationRequestDto;
import me.hu6r1s.oauth2.dto.request.IdCheckRequestDto;
import me.hu6r1s.oauth2.dto.request.MailCertificationRequestDto;
import me.hu6r1s.oauth2.dto.request.SignInRequestDto;
import me.hu6r1s.oauth2.dto.request.SignUpRequestDto;
import me.hu6r1s.oauth2.dto.response.CertificationResponseDto;
import me.hu6r1s.oauth2.dto.response.SignInResponseDto;
import me.hu6r1s.oauth2.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "JWT 로그인 / 회원가입 API 명세서", description = "JWT를 이용한 로그인 및 회원가입 API 명세입니다.")
public class AuthController {

  private final AuthService authService;

  @Operation(summary = "유저 아이디 중복 체크", description = "유저 아이디가 중복되었는지 확인하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Duplication Id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
      @ApiResponse(responseCode = "500", description = "Database error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
  })
  @PostMapping("/id-check")
  public ResponseEntity<CertificationResponseDto> idCheck(
      @RequestBody @Valid IdCheckRequestDto requestDto
  ) {
    authService.idCheck(requestDto);
    return CertificationResponseDto.success();
  }

  @Operation(summary = "이메일 인증", description = "이메일로 인증 코드를 보내는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Duplication Id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
      @ApiResponse(responseCode = "500", description = "Mail Send failed", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
      @ApiResponse(responseCode = "500", description = "Database error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
  })
  @PostMapping("/email-certification")
  public ResponseEntity<CertificationResponseDto> mailCertification(
      @RequestBody @Valid MailCertificationRequestDto requestDto
  ) {
    authService.mailCertification(requestDto);
    return CertificationResponseDto.success();
  }

  @Operation(summary = "이메일 인증 코드 체크", description = "이메일로 온 인증 코드를 확인하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
      @ApiResponse(responseCode = "401", description = "Certification failed", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
      @ApiResponse(responseCode = "500", description = "Database error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
  })
  @PostMapping("/check-certification")
  public ResponseEntity<CertificationResponseDto> checkCertification(
      @RequestBody @Valid CheckCertificationRequestDto requestDto
  ) {
    authService.checkCertification(requestDto);
    return CertificationResponseDto.success();
  }

  @Operation(summary = "회원가입", description = "유저 회원가입 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Duplication", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
      @ApiResponse(responseCode = "401", description = "Certification failed", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
      @ApiResponse(responseCode = "500", description = "Database error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
  })
  @PostMapping("/signup")
  public ResponseEntity<CertificationResponseDto> signup(
      @RequestBody @Valid SignUpRequestDto requestDto
  ) {
    authService.signup(requestDto);
    return CertificationResponseDto.success();
  }

  @Operation(summary = "로그인", description = "유저 로그인 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SignInResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
      @ApiResponse(responseCode = "401", description = "Login information mismatch", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
      @ApiResponse(responseCode = "500", description = "Database error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CertificationResponseDto.class))),
  })
  @PostMapping("/signin")
  public ResponseEntity<SignInResponseDto> signin(
      @RequestBody @Valid SignInRequestDto requestDto
  ) {
    String token = authService.signin(requestDto);
    return SignInResponseDto.success(token);
  }
}
