package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.*;
import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.exception.ScheduleNotFoundException;
import com.example.scheduledevelop.repository.ScheduleRepository;
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

    // 생

    // 기
    // C
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequestDto requestDto) {

        // .trim() : 앞뒤에 붙어잇는 쓸덷없는 공백을 잘라주는 메서드
        // .isEmpty() : 문자열의 길이가 0인지 확인
        // .trim().isEmpty() : 스페이스만 잔뜩 입력해서 보낸 얌체 데이터도 아무것도 입력 안했다고 판정
        if(requestDto.getTitle() == null || requestDto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("제목은 무조건 입력해야 합니다.");
        }else if(requestDto.getTitle().length() > 200) {
            throw new IllegalArgumentException("제목이 너무 길어서 200자 이하여야 합니다.");
        }

        if(requestDto.getContent() == null || requestDto.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("내용을 무조건 입력해야 합니다.");
        }

        if(requestDto.getName() == null || requestDto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("작성자명은 무조건 입력해야 합니다.");
        }

        // 받은 데이터를 꺼내기
        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getName()
        );

        // 꺼낸 데이터를 저장하기
        Schedule saved = scheduleRepository.save(schedule);

        // 들어온 데이터를 보여주기(응답)
        return new CreateScheduleResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getName(),
                saved.getCreateAt(),
                saved.getUpdateAt()
        );
    }

    // R
    @Transactional(readOnly = true)
    public List<GetAllScheduleResponseDto> findAll() {
        List<Schedule> scheduleList = scheduleRepository.findAll();

        return scheduleList.stream().map(GetAllScheduleResponseDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public GetAllScheduleResponseDto findOne(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ScheduleNotFoundException("없는 일정입니다.")
        );
        return GetAllScheduleResponseDto.from(schedule);
    }

    // U
    // 업데이트 수정
    @Transactional
    public UpdateScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto requestDto) {

        if(requestDto.getTitle() == null || requestDto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("제목은 무조건 입력해야 합니다.");
        }else if(requestDto.getTitle().length() > 200) {
            throw new IllegalArgumentException("제목이 너무 길어서 200자 이하여야 합니다.");
        }

        if(requestDto.getContent() == null || requestDto.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("내용을 무조건 입력해야 합니다.");

        }


        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ScheduleNotFoundException("없는 일정입니다.")
        );


            schedule.update(
                requestDto.getTitle(),
                requestDto.getContent()
        );

        return UpdateScheduleResponseDto.from(schedule);

    }

    // D
    public void delete(Long id) {
        scheduleRepository.findById(id).orElseThrow(
                () -> new ScheduleNotFoundException("없는 일정입니다.")
        );
        scheduleRepository.deleteById(id);
    }
}
