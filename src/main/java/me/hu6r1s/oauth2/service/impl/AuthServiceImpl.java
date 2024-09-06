package me.hu6r1s.oauth2.service.impl;

import lombok.RequiredArgsConstructor;
import me.hu6r1s.oauth2.dto.request.IdCheckRequestDto;
import me.hu6r1s.oauth2.global.handler.CustomException;
import me.hu6r1s.oauth2.global.response.ResponseCode;
import me.hu6r1s.oauth2.repository.UserRepository;
import me.hu6r1s.oauth2.service.AuthService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;

  @Override
  public void idCheck(IdCheckRequestDto requestDto) {
    try {
      String userId = requestDto.getUserId();
      boolean isExistId = userRepository.existsByUserId(userId);
      if (isExistId) {
        throw new CustomException(ResponseCode.DUPLICATE_ID.getCode(),
            ResponseCode.DUPLICATE_ID.getStatus(), ResponseCode.DUPLICATE_ID.getMessage());
      }
    } catch (DataAccessException e) {
      throw new CustomException(ResponseCode.DATABASE_ERROR.getCode(),
          ResponseCode.DATABASE_ERROR.getStatus(), ResponseCode.DATABASE_ERROR.getMessage());
    }
  }
}
