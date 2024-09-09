package me.hu6r1s.oauth2.repository;

import me.hu6r1s.oauth2.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {


  Certification findByUserId(String userId);

  void deleteByUserId(String userId);
}
