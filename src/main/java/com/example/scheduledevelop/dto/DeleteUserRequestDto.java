package com.example.scheduledevelop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteUserRequestDto {

    @NotBlank(message = "비밀번호를 입력해야 삭제가 가능합니다.")
    private String password;
}
