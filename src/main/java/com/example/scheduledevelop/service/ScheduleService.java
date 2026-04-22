package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.*;
import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.exception.ScheduleNotFoundException;
import com.example.scheduledevelop.exception.UserNotFoundException;
import com.example.scheduledevelop.repository.ScheduleRepository;
import com.example.scheduledevelop.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 일정 관리 비즈니스 로직하는 서비스 클래스입니다.
 * 이 클래스는 {@link ScheduleRepository}를 사용하여
 * 일정 생성, 조회, 수정, 삭제 (CRUD) 기능을 제공합니다.
 */
@Service
@RequiredArgsConstructor
public class ScheduleService {

    // 속
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 생

    // 기
    // C
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequestDto requestDto, Long sessionUserId) {

        if(!requestDto.getUserId().equals(sessionUserId)) {
            throw new IllegalArgumentException("본인의 계정으로만 일정을 생성할 수 있습니다.");
        }

        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );
        // 받은 데이터를 꺼내기
        Schedule schedule = new Schedule(
                user,
                requestDto.getTitle(),
                requestDto.getContent()
        );

        // 꺼낸 데이터를 저장하기
        Schedule saved = scheduleRepository.save(schedule);

        // 들어온 데이터를 보여주기(응답)
        return new CreateScheduleResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                requestDto.getUserId(),
                saved.getCreateAt(),
                saved.getUpdateAt()
        );
    }

    // R
    @Transactional(readOnly = true)
    public List<GetAllScheduleResponseDto> findAll(Long sessionUserId) {
        List<Schedule> scheduleList = scheduleRepository.findAllByUser_userId(sessionUserId);


        return scheduleList.stream()
                .map(GetAllScheduleResponseDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public FindByOneScheduleResponseDto findOne(Long id, Long sessionUserId) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ScheduleNotFoundException("없는 일정입니다.")
        );

        if(!sessionUserId.equals(schedule.getUser().getUserId())) {
            throw new IllegalArgumentException("작성자 본인이 아닙니다.");
        }

        return FindByOneScheduleResponseDto.from(schedule);
    }

    // U
    // 업데이트 수정
    @Transactional
    public UpdateScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto requestDto, Long sessionUserId) {

        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ScheduleNotFoundException("없는 일정입니다.")
        );

        if(!sessionUserId.equals(schedule.getUser().getUserId())) {
            throw new IllegalArgumentException("작성자 본인이 아닙니다.");
        }

        schedule.update(
                requestDto.getTitle(),
                requestDto.getContent()
        );

        return UpdateScheduleResponseDto.from(schedule);

    }

    // D
    public void delete(Long id, Long sessionUserId) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ScheduleNotFoundException("없는 일정입니다.")
        );

        // 비번일치 검증
        if(!sessionUserId.equals(schedule.getUser().getUserId())) {
            throw new IllegalArgumentException("작성자 본인이 아닙니다.");
        }

        scheduleRepository.deleteById(id);
    }
}
