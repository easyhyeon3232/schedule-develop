package com.example.scheduledevelop.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateUserRequestDto {

    private final String userName;
    private final String email;
}
