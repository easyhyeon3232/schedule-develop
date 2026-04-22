package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.LoginRequestDto;
import com.example.scheduledevelop.dto.LoginResponseDto;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 로그인 관리 비즈니스 로직하는 서비스 클래스입니다.
 * 이 클래스는 {@link UserRepository}를 사용하여
 * 로그인 기능을 제공합니다.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;


    public LoginResponseDto login (LoginRequestDto loginRequestDto) {
        // 이메일 찾기
        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("이메일이 일치하지 않습니다.")
        );

        if(!(loginRequestDto.getPassword().equals(user.getPassword()))) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return LoginResponseDto.from(user);
    }
}
