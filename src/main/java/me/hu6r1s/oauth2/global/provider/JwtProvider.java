package me.hu6r1s.oauth2.global.provider;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

  @Value("${jwt.access-token-time}")
  private long ACCESS_TOKEN_TIME;

  private final SecretKey secretKey;

  public JwtProvider(@Value("${jwt.secret-key}") String secret) {
    this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
        SIG.HS256.key().build().getAlgorithm());
  }

  public String create(String userId) {
    Date expiredDate = Date.from(Instant.now().plus(ACCESS_TOKEN_TIME, ChronoUnit.HOURS));

    return Jwts.builder()
        .signWith(secretKey)
        .subject(userId).issuedAt(new Date()).expiration(expiredDate)
        .compact();
  }

  public String validate(String token) {
    String subject = null;

    try {
      subject = Jwts.parser()
          .verifyWith(secretKey)
          .build()
          .parseSignedClaims(token)
          .getPayload()
          .getSubject();
    } catch (JwtException | IllegalArgumentException e) {
      throw new RuntimeException(e);
    }

    return subject;
  }
}
