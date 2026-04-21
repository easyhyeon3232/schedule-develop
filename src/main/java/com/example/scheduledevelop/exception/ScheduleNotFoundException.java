package com.example.scheduledevelop.exception;

import org.springframework.http.HttpStatus;

/**
 * 요청한 일정 정보를 시스템에서 찾을 수 없을 때 발생하는 예외입니다.
 * {@link ServiceException}을 상속받으며, {@link HttpStatus#NOT_FOUND}를 사용합니다.
 */
public class ScheduleNotFoundException extends ServiceException {

    /**
     * 에러 메시지를 받아 Not Found 상태를 가진 예외를 생성합니다.
     * @param message "없는 일정입니다."의 상세 에러 메시지
     */
    public ScheduleNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
