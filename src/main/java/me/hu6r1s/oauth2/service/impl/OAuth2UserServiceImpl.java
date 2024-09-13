package me.hu6r1s.oauth2.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import me.hu6r1s.oauth2.entity.CustomOAuth2User;
import me.hu6r1s.oauth2.entity.User;
import me.hu6r1s.oauth2.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {

  private final UserRepository userRepository;

  @Override
  @Transactional
  public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(request);
    String oauthClientName = request.getClientRegistration().getClientName();
    try {
      System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    User user = null;
    String userId = null;
    String email = null;

    if (oauthClientName.equals("kakao")) {
      userId = "kakao_" + oAuth2User.getAttributes().get("id");
      Map<String, String> kakaoAccount = (Map<String, String>) oAuth2User.getAttributes().get("kakao_account");
      email = kakaoAccount.get("email").toString();
      user = new User(userId, email, "password", "kakao");
    }

    if (oauthClientName.equals("naver")) {
      Map<String, String> responseMap = (Map<String, String>) oAuth2User.getAttributes().get("response");
      userId = "naver_" + responseMap.get("id").substring(0, 14);
      email = responseMap.get("email");
      user = new User(userId, email, "password", "naver");
    }

    userRepository.save(user);

    return new CustomOAuth2User(userId);
  }
}
