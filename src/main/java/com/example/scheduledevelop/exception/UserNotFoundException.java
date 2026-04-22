package com.example.scheduledevelop.exception;

import org.springframework.http.HttpStatus;

/**
 * 요청한 유저 정보를 시스템에서 찾을 수 없을 때 발생하는 예외입니다.
 * {@link ServiceException}을 상속받으며, {@link HttpStatus#NOT_FOUND}를 사용합니다.
 */
public class UserNotFoundException extends ServiceException {
    public UserNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
