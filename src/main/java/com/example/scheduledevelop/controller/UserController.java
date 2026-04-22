package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.*;
import com.example.scheduledevelop.repository.UserRepository;
import com.example.scheduledevelop.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 유저 관리 관련 HTTP 요청을 처리하는 컨트롤러입니다.
 * 모든 요청은 "/users" 경로를 기본으로 합니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    // 속
    private final UserService userService;
    private final UserRepository userRepository;
    // 생

    // 기
    @PostMapping
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody CreateUserRequestDto requestDto) {
        CreateUserResponseDto userResponseDto = userService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<FindAllUserResponseDto>> findAll(HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        if(sessionUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

            List<FindAllUserResponseDto> all = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindOneUserResponseDto> findOne(@PathVariable Long id, HttpSession session) {

        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        if(sessionUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        FindOneUserResponseDto userResponseDto = userService.findOne(id, sessionUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateUserResponseDto> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequestDto updateUserRequestDto, HttpSession session) {

        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        if(sessionUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        UpdateUserResponseDto update = userService.update(id, updateUserRequestDto, sessionUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, @RequestBody DeleteUserRequestDto deleteUserRequestDto, HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        if(sessionUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        userService.delete(id, deleteUserRequestDto, sessionUser.getId());

        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
