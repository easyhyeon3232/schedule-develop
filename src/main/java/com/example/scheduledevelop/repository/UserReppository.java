package com.example.scheduledevelop.repository;

import com.example.scheduledevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 유저 엔티티(User)에 대한 DB 엑세스 처리를 담당하는 레포지토리 인터페이스입니다.
 * Spring Data JPA의 JpaRepository를 상속받아 기본적인 CRUD 기능을 제공합니다.
 */
public interface UserReppository extends JpaRepository<User, Long> {
}
