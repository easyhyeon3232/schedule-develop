package com.example.scheduledevelop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateUserRequestDto {

    @Pattern(regexp = "^[가-힣]{2,10}$",
            message = "이름은 한글 2~10자로 입력하세요")
    private final String userName;

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private final String email;

    @NotBlank(message = "비밀번호를 입력해야 수정가 가능합니다.")
    private final String password;
}
