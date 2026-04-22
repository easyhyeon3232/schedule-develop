package com.example.scheduledevelop.dto;

import com.example.scheduledevelop.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class FindOneUserResponseDto {

    private final Long userId;
    private final String userName;
    private final String email;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    public static FindOneUserResponseDto from (User user) {
        return new FindOneUserResponseDto(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreateAt(),
                user.getUpdateAt()
        );

    }

}
