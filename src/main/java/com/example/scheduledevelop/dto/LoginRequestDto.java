package com.example.scheduledevelop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequestDto {

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private final String email;


    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$",
            message = "비밀번호는 영문, 숫자를 포함한 8자 이상이어야 합니다")
    private final String password;
}
