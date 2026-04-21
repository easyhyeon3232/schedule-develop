package com.example.scheduledevelop.dto;

import com.example.scheduledevelop.entity.Schedule;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    @Builder(access = AccessLevel.PRIVATE)
    public UpdateScheduleResponseDto(Long id, String title, String content, String name, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }


    public static UpdateScheduleResponseDto from (Schedule schedule) {
        return UpdateScheduleResponseDto.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .name(schedule.getName())
                .createAt(schedule.getCreateAt())
                .updateAt(schedule.getUpdateAt())
                .build();

    }
}
