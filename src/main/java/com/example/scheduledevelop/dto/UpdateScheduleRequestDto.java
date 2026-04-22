package com.example.scheduledevelop.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateScheduleRequestDto {

    private final String title;
    private final String content;
    private final String password;

}
