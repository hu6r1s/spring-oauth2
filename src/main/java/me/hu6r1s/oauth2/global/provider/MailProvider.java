package me.hu6r1s.oauth2.global.provider;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailProvider {

  private final JavaMailSender javaMailSender;
  private final String SUBJECT = "[TEST 메일] 인증메일";

  public boolean sendCertificationMail(String mail, String certificationNumber) {
    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

      String htmlContent = getCertificationMessage(certificationNumber);

      messageHelper.setTo(mail);
      messageHelper.setSubject(SUBJECT);
      messageHelper.setText(htmlContent, true);

      javaMailSender.send(message);
    } catch (MessagingException e) {
      return false;
    }

    return true;
  }

  private String getCertificationMessage(String certificationNumber) {
    String certificationMessage = "";
    certificationMessage += "<h1 style='text-align: center;'>[TEST 메일] 인증메일 테스트 제목</h1>";
    certificationMessage +=
        "<h3 style='text-align: center'>인증코드 : <strong style='font-size: 32px; letter-spacing: 8px;'>"
            + certificationNumber + "</strong></h3>";
    return certificationMessage;
  }

  public String getCertificationNumber() {
    String certificationNumber = "";
    for (int count = 0; count < 4; count++) {
      certificationNumber += (int) (Math.random() * 10);
    }
    return certificationNumber;
  }
}
