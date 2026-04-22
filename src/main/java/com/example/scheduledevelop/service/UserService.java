package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.*;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.exception.UserNotFoundException;
import com.example.scheduledevelop.repository.ScheduleRepository;
import com.example.scheduledevelop.repository.UserReppository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 유저 관리 비즈니스 로직하는 서비스 클래스입니다.
 * 이 클래스는 {@link UserReppository}를 사용하여
 * 유저 생성, 조회, 수정, 삭제 (CRUD) 기능을 제공합니다.
 */
@RequiredArgsConstructor
@Service
public class UserService {
    // 속
    private final UserReppository userReppository;

    // 생

    // 기
    //C
    @Transactional
    public CreateUserResponseDto create(CreateUserRequestDto requestDto) {

        if(requestDto.getUserName() == null || requestDto.getUserName().trim().isEmpty()) {
            throw new IllegalArgumentException("이름은 무조건 입력해야 합니다.");
        }

        if(requestDto.getEmail() == null || requestDto.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("이메일은 무조건 입력해야 합니다.");
        }

        if(requestDto.getPassword() == null || requestDto.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 무조건 입력해야 합니다.");
        }

        if (requestDto.getPassword().length() < 8) {
            throw  new IllegalArgumentException("비밀번호는 무조건 8글자 이상이어야 합니다.");
        }

        User user = new User(requestDto.getUserName(),
                requestDto.getEmail(),
                requestDto.getPassword());
        User saved = userReppository.save(user);
        return CreateUserResponseDto.from(saved);
    }

    //R
    @Transactional(readOnly = true)
    public List<FindAllUserResponseDto> findAll() {
        List<User> userList = userReppository.findAll();

        return userList.stream()
                .map(FindAllUserResponseDto::from).toList();
    }

    @Transactional(readOnly = true)
    public FindOneUserResponseDto findOne(Long id) {
        User user = userReppository.findById(id).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );

        return FindOneUserResponseDto.from(user);
    }


    //U
    @Transactional
    public UpdateUserResponseDto update(Long id, UpdateUserRequestDto updateUserRequestDto) {

        if(updateUserRequestDto.getUserName() == null || updateUserRequestDto.getUserName().trim().isEmpty()) {
            throw new IllegalArgumentException("이름은 무조건 입력해야 합니다.");
        }

        if(updateUserRequestDto.getEmail() == null || updateUserRequestDto.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("이메일은 무조건 입력해야 합니다.");
        }

        if(updateUserRequestDto.getPassword() == null || updateUserRequestDto.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 무조건 입력해야 합니다.");
        }

        if (updateUserRequestDto.getPassword().length() < 8) {
            throw  new IllegalArgumentException("비밀번호는 무조건 8글자 이상이어야 합니다.");
        }

        User user = userReppository.findById(id).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );

        if (!(updateUserRequestDto.getPassword().equals(user.getPassword()))) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        user.update(
                updateUserRequestDto.getUserName(),
                updateUserRequestDto.getEmail()
        );
        return UpdateUserResponseDto.from(user);

    }

    //D
    @Transactional
    public void delete(Long id, DeleteUserRequestDto deleteUserRequestDto) {
        User user = userReppository.findById(id).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );

        if (!(deleteUserRequestDto.getPassword().equals(user.getPassword()))) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        userReppository.delete(user);
    }

}
