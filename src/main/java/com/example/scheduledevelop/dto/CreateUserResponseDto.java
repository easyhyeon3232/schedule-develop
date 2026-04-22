package com.example.scheduledevelop.dto;

import com.example.scheduledevelop.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateUserResponseDto {
    private final Long userId;
    private final String userName;
    private final String email;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    @Builder(access = AccessLevel.PRIVATE)
    public CreateUserResponseDto(Long userId, String userName, String email, LocalDateTime createAt, LocalDateTime updateAt) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public static CreateUserResponseDto from (User user) {
        return CreateUserResponseDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .build();
    }
}
