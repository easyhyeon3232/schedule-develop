package com.example.scheduledevelop.dto;

import com.example.scheduledevelop.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * 일정을 생성하기 위한 요청 데이터 전송 객체(DTO)입니다.
 * 클라이언트로부터 받은 일정 제목, 내용, 작성자 이름을 담습니다.
 * 이 클래스는 필드의 불변성을 유지하기 위해 모든 필드를 final로 선언하며,
 * Lombok의 {@code @RequiredArgsConstructor}를 통해 생성자를 자동 생성합니다.
 */
@Getter
@RequiredArgsConstructor
public class CreateScheduleRequestDto {

    private final Long userId;

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 100, message = "제목은 100자 이내여야 합니다.")
    private final String title;

    @NotBlank(message = "내용을 입력하셔야 합니다.")
    private final String content;
}
