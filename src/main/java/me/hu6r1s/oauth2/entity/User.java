package me.hu6r1s.oauth2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String userId;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, unique = true)
  private String email;

  @Column
  private String type;

  @Column
  @Enumerated(EnumType.STRING)
  private UserRole role;

  public User(String userId, String password, String email) {
    this.userId = userId;
    this.password = password;
    this.email = email;
    this.type = "app";
    this.role = UserRole.USER;
  }

  public User(String userId, String email, String password, String type) {
    this.userId = userId;
    this.password = password;
    this.email = email;
    this.type = type;
    this.role = UserRole.USER;
  }
}
