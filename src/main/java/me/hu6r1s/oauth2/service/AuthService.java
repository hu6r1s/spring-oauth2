package me.hu6r1s.oauth2.service;

import me.hu6r1s.oauth2.dto.request.IdCheckRequestDto;

public interface AuthService {

  void idCheck(IdCheckRequestDto reqeustDto);
}
