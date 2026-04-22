package com.example.scheduledevelop.service;

import com.example.scheduledevelop.config.PasswordEncoder;
import com.example.scheduledevelop.dto.*;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.exception.UserNotFoundException;
import com.example.scheduledevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 유저 관리 비즈니스 로직하는 서비스 클래스입니다.
 * 이 클래스는 {@link UserRepository}를 사용하여
 * 유저 생성, 조회, 수정, 삭제 (CRUD) 기능을 제공합니다.
 */
@RequiredArgsConstructor
@Service
public class UserService {
    // 속
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 생

    // 기
    //C
    @Transactional
    public CreateUserResponseDto create(CreateUserRequestDto requestDto) {

        if(userRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이미 있는 이메일입니다.");
        }

        String encode = passwordEncoder.encode((requestDto.getPassword()));
        User user = new User(requestDto.getUserName(),
                requestDto.getEmail(),
                encode);

        User saved = userRepository.save(user);
        return CreateUserResponseDto.from(saved);
    }

    //R
    @Transactional(readOnly = true)
    public List<FindAllUserResponseDto> findAll() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(FindAllUserResponseDto::from).toList();
    }

    @Transactional(readOnly = true)
    public FindOneUserResponseDto findOne(Long id, Long sessionUserId) {

        if(!id.equals(sessionUserId)) {
            throw new IllegalArgumentException("본인의 정보만 확인할 수 있습니다.");
        }
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );

        return FindOneUserResponseDto.from(user);
    }


    //U
    @Transactional
    public UpdateUserResponseDto update(Long id, UpdateUserRequestDto updateUserRequestDto, Long sessionUserId) {

        if(!id.equals(sessionUserId)) {
            throw new IllegalArgumentException("본인의 정보만 수정할 수 있습니다.");
        }

        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );

        // 이메일은 아이디가 되어버려서 수정하면 안됨
        if(!updateUserRequestDto.getEmail().equals(user.getEmail())) {
            throw new IllegalArgumentException("이메일이 일치하지 않습니다.");
        }

        if(!passwordEncoder.matches(updateUserRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        user.update(
                updateUserRequestDto.getUserName()
        );
        return UpdateUserResponseDto.from(user);

    }

    //D
    @Transactional
    public void delete(Long id, DeleteUserRequestDto deleteUserRequestDto, Long sessionUserId) {

        if(!id.equals(sessionUserId)) {
            throw new IllegalArgumentException("본인의 정보만 삭제할 수 있습니다.");
        }

        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );

        if(!passwordEncoder.matches(deleteUserRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        userRepository.delete(user);
    }

}
