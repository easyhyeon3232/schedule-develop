package com.example.scheduledevelop.dto;

import com.example.scheduledevelop.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final Long userId;
    private final String email;
    private final String name;

    @Builder
    public LoginResponseDto(Long userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }

    public static LoginResponseDto from (User user) {
        return LoginResponseDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getUserName())
                .build();
    }
}
