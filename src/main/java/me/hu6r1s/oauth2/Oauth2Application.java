package me.hu6r1s.oauth2;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "OAuth2.0 및 JWT 로그인 / 회원가입 API 명세서", description = "OAuth2.0, JWT 로그인 / 회원가입 학습하기 위한 API 명세서", version = "v1"))
public class Oauth2Application {

  public static void main(String[] args) {
    SpringApplication.run(Oauth2Application.class, args);
  }

}
