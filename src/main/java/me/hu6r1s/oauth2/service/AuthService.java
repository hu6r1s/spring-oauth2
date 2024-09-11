package me.hu6r1s.oauth2.service;

import me.hu6r1s.oauth2.dto.request.CheckCertificationRequestDto;
import me.hu6r1s.oauth2.dto.request.IdCheckRequestDto;
import me.hu6r1s.oauth2.dto.request.MailCertificationRequestDto;
import me.hu6r1s.oauth2.dto.request.SignInRequestDto;
import me.hu6r1s.oauth2.dto.request.SignUpRequestDto;
import me.hu6r1s.oauth2.dto.response.SignInResponseDto;

public interface AuthService {

  void idCheck(IdCheckRequestDto reqeustDto);

  void mailCertification(MailCertificationRequestDto requestDto);

  void checkCertification(CheckCertificationRequestDto requestDto);

  void signup(SignUpRequestDto requestDto);

  String signin(SignInRequestDto requestDto);
}
