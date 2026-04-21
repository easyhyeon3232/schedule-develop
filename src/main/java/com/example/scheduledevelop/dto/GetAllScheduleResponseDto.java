package com.example.scheduledevelop.dto;

import com.example.scheduledevelop.entity.Schedule;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetAllScheduleResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    public GetAllScheduleResponseDto(Long id, String title, String content, String name, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.createAt = createAt;
        this.updateAt = updateAt;

    }

    // 정적 메서드
    public static GetAllScheduleResponseDto from(Schedule schedule) {
        return new GetAllScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getCreateAt(),
                schedule.getUpdateAt()
        );
    }
}
