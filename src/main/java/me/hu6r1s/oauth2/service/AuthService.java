package me.hu6r1s.oauth2.service;

import me.hu6r1s.oauth2.dto.request.IdCheckRequestDto;
import me.hu6r1s.oauth2.dto.request.MailCertificationRequestDto;

public interface AuthService {

  void idCheck(IdCheckRequestDto reqeustDto);

  void mailCertification(MailCertificationRequestDto requestDto);
}
