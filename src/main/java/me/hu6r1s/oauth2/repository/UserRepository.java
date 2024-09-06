package me.hu6r1s.oauth2.repository;

import me.hu6r1s.oauth2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUserId(String userId);

  boolean existsByUserId(String userId);
}
