package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.*;
import com.example.scheduledevelop.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 일정 관리 관련 HTTP 요청을 처리하는 컨트롤러입니다.
 * 모든 요청은 "/schedules" 경로를 기본으로 합니다.
 */
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    // 속
    private final ScheduleService scheduleService;

    // 생

    // 기

    /**
     * 새로운 일정을 생성합니다.
     * @param session
     * @param createRequestDto 생성할 일정의 정보를 담은 DTO
     * @return 생성된 일정 정보와 함께 201 Created 상태 코드를 반환
     */
    @PostMapping
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequestDto createRequestDto, HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        if(sessionUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        CreateScheduleResponse save = scheduleService.save(createRequestDto, sessionUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    /**
     * 전체 일정 목록을 조회합니다.
     * @return 조회된 일정 목록 리스트와 함께 200 ok 상태 코드를 반환
     */
    @GetMapping
    public ResponseEntity<List<GetAllScheduleResponseDto>> getSchedule(HttpSession session) {

        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        if(sessionUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        List<GetAllScheduleResponseDto> all = scheduleService.findAll(sessionUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    /**
     * 특정 식별자(ID)를 가진 일정을 상세 조회합니다.
     * @param id 조회할 일정의 고유의 식별자(ID)
     * @return 조회된 일정 상세 정보와 함께 200 ok 상태 코드를 반환
     */
    @GetMapping("/{id}")

    // 로그인 확인
    public ResponseEntity<FindByOneScheduleResponseDto> findByOnd(@PathVariable Long id, HttpSession session) {

        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        if(sessionUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }


        FindByOneScheduleResponseDto responseDto = scheduleService.findOne(id, sessionUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    /**
     * 특정 일정의 정보를 부분 수정합니다.
     * @param id 수정할 일정의 고유 식별자(ID)
     * @param requestDto 수정할 정보를 담은 DTO
     * @return 수정 완료된 일정 정보와 함께 200 ok 상태 코드를 반환
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UpdateScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody UpdateScheduleRequestDto requestDto, HttpSession session) {

        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        if(sessionUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        UpdateScheduleResponseDto responseDto = scheduleService.updateSchedule(id, requestDto, sessionUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    /**
     * 특정 일정을 삭제합니다.
     * @param id 삭제할 일정의 고유 식별자(ID)
     * @return 성공 시 204 No Content 상태 코드를 반환
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpSession session) {

        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        if(sessionUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        scheduleService.delete(id, sessionUser.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
