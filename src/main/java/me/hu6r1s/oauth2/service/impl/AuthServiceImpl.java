package me.hu6r1s.oauth2.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.hu6r1s.oauth2.dto.request.CheckCertificationRequestDto;
import me.hu6r1s.oauth2.dto.request.IdCheckRequestDto;
import me.hu6r1s.oauth2.dto.request.MailCertificationRequestDto;
import me.hu6r1s.oauth2.dto.request.SignUpRequestDto;
import me.hu6r1s.oauth2.entity.Certification;
import me.hu6r1s.oauth2.entity.User;
import me.hu6r1s.oauth2.global.handler.CustomException;
import me.hu6r1s.oauth2.global.provider.MailProvider;
import me.hu6r1s.oauth2.global.response.ResponseCode;
import me.hu6r1s.oauth2.repository.CertificationRepository;
import me.hu6r1s.oauth2.repository.UserRepository;
import me.hu6r1s.oauth2.service.AuthService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final CertificationRepository certificationRepository;
  private final MailProvider mailProvider;
  private final PasswordEncoder passwordEncoder;

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

  @Override
  @Transactional
  public void mailCertification(MailCertificationRequestDto requestDto) {
    try {
      String userId = requestDto.getUserId();
      String email = requestDto.getEmail();

      boolean isExistId = userRepository.existsByUserId(userId);
      if (isExistId) {
        throw new CustomException(ResponseCode.DUPLICATE_ID.getCode(),
            ResponseCode.DUPLICATE_ID.getStatus(), ResponseCode.DUPLICATE_ID.getMessage());
      }

      String certificationNumber = mailProvider.getCertificationNumber();

      boolean isSended = mailProvider.sendCertificationMail(email, certificationNumber);
      if (!isSended) {
        throw new CustomException(ResponseCode.MAIL_FAIL.getCode(),
            ResponseCode.MAIL_FAIL.getStatus(), ResponseCode.MAIL_FAIL.getMessage());
      }

      Certification certification = new Certification(userId, email, certificationNumber);
      certificationRepository.save(certification);
    } catch (DataAccessException e) {
      throw new CustomException(ResponseCode.DATABASE_ERROR.getCode(),
          ResponseCode.DATABASE_ERROR.getStatus(), ResponseCode.DATABASE_ERROR.getMessage());
    }
  }

  @Override
  public void checkCertification(CheckCertificationRequestDto requestDto) {
    try {
      String userId = requestDto.getUserId();
      String email = requestDto.getEmail();
      String certificationNumber = requestDto.getCertificationNumber();

      Certification certification = certificationRepository.findByUserId(userId);
      if (certification == null) {
        throw new CustomException(ResponseCode.CERTIFICATION_FAIL.getCode(),
            ResponseCode.CERTIFICATION_FAIL.getStatus(),
            ResponseCode.CERTIFICATION_FAIL.getMessage());
      }

      boolean isMatched =
          certification.getEmail().equals(email) && certification.getCertificationNumber()
              .equals(certificationNumber);
      if (!isMatched) {
        throw new CustomException(ResponseCode.CERTIFICATION_FAIL.getCode(),
            ResponseCode.CERTIFICATION_FAIL.getStatus(),
            ResponseCode.CERTIFICATION_FAIL.getMessage());
      }
    } catch (DataAccessException e) {
      throw new CustomException(ResponseCode.DATABASE_ERROR.getCode(),
          ResponseCode.DATABASE_ERROR.getStatus(), ResponseCode.DATABASE_ERROR.getMessage());
    }
  }

  @Override
  @Transactional
  public void signup(SignUpRequestDto requestDto) {
    try {
      String userId = requestDto.getUserId();
      boolean isExistId = userRepository.existsByUserId(userId);
      if (isExistId) {
        throw new CustomException(ResponseCode.DUPLICATE_ID.getCode(),
            ResponseCode.DUPLICATE_ID.getStatus(), ResponseCode.DUPLICATE_ID.getMessage());
      }

      String email = requestDto.getEmail();
      String certificationNumber = requestDto.getCertificationNumber();

      Certification certification = certificationRepository.findByUserId(userId);
      boolean isMatched =
          certification.getEmail().equals(email) && certification.getCertificationNumber()
              .equals(certificationNumber);
      if (!isMatched) {
        throw new CustomException(ResponseCode.CERTIFICATION_FAIL.getCode(),
            ResponseCode.CERTIFICATION_FAIL.getStatus(),
            ResponseCode.CERTIFICATION_FAIL.getMessage());
      }

      String password = requestDto.getPassword();
      String encodedPassword = passwordEncoder.encode(password);

      User user = new User(userId, encodedPassword, email);
      userRepository.save(user);
      certificationRepository.deleteByUserId(userId);
    } catch (DataAccessException e) {
      throw new CustomException(ResponseCode.DATABASE_ERROR.getCode(),
          ResponseCode.DATABASE_ERROR.getStatus(), ResponseCode.DATABASE_ERROR.getMessage());
    }
  }
}
