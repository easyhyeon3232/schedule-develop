package com.example.scheduledevelop.dto;

import com.example.scheduledevelop.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * /**
 * 일정을 생성 성공 시 클라이언트에게 반환되는 응답 데이터 전송 객체(DTO)입니다.
 * 저장된 일정의 식별자(ID)와 작성일/수정일를 포함한 전체 정보를 담고 있습니다.
 * 이 클래스는 필드의 불변성을 유지하기 위해 모든 필드를 final로 선언하고 있습니다.
 */
@Getter
public class CreateScheduleResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final Long userId;
    // DB생성 시 (datetime(6)으로 저장된다.)
    private final LocalDateTime createAt;

    // 생성 직후에는 createAt과 동일한 값을 가진다.
    private final LocalDateTime updateAt;

    /**
     * 전체 필드를 초기화하는 생성자입니다.
     * @param id 일정 식별자
     * @param title 제목
     * @param content 내용
     * @param userId 유저 고유 식별자
     * @param createAt 등록 일시
     * @param updateAt 수정 일시
     */
    public CreateScheduleResponse(Long id, String title, String content, Long userId, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
