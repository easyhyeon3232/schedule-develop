package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.LoginRequestDto;
import com.example.scheduledevelop.dto.LoginResponseDto;
import com.example.scheduledevelop.dto.SessionUser;
import com.example.scheduledevelop.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpSession session) {  // 로그인이 성공하면 session에 담긴다.
        LoginResponseDto responseDto = authService.login(loginRequestDto);

        // 로그인한 사람의 정보 중 딱 필요한 것만 골라 sessionUser에 담겠다.
        SessionUser sessionUser = new SessionUser(responseDto.getUserId(), responseDto.getEmail());

        // 키값은 "sessionUser"이고 value값은 sessionUser로 안에 있는 데이터를 session에 저장한다.
        session.setAttribute("sessionUser", sessionUser);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }
}
