package me.hu6r1s.oauth2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "certifications")
public class Certification {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String email;

  @Column
  private String certificationNumber;

  @Column(name = "user_id")
  private String userId;

  public Certification(String userId, String email, String certificationNumber) {
    this.userId = userId;
    this.email = email;
    this.certificationNumber = certificationNumber;
  }
}
