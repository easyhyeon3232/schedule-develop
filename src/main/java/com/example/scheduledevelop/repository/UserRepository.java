package com.example.scheduledevelop.repository;

import com.example.scheduledevelop.entity.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 유저 엔티티(User)에 대한 DB 엑세스 처리를 담당하는 레포지토리 인터페이스입니다.
 * Spring Data JPA의 JpaRepository를 상속받아 기본적인 CRUD 기능을 제공합니다.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(@Email(message = "올바른 이메일 형식이 아닙니다.") String email);
}
