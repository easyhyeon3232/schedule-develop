package com.example.scheduledevelop.repository;

import com.example.scheduledevelop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 일정 엔티티(Schedule)에 대한 DB 엑세스 처리를 담당하는 레포지토리 인터페이스입니다.
 * Spring Data JPA의 JpaRepository를 상속받아 기본적인 CRUD 기능을 제공합니다.
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByUser_userId(Long sessionUserId);
}
