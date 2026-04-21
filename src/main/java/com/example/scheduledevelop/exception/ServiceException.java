package com.example.scheduledevelop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 서비스 비즈니스 로직 처리 중 발생하는 공통 예외 클래스입니다.
 * {@link RuntimeException}을 상속받았습니다.
 * 발생한 에러에 적합한 {@link HttpStatus}를 포함합니다.
 * 이 클래스를 상속받아 각 도메인에 맞는 구체적인 예외들을 정의할 수 있습니다.
 */
@Getter
public class ServiceException extends RuntimeException {

    // 예외 발생 시 반환할 HTTP 응답 상태 코드
    private final HttpStatus status;

    /**
     * 상태 코드와 메시지를 지정하여 예외를 생성합니다.
     * @param status HTTP 응답 상태 코드(예: 404, 400 등)
     * @param message 에러 상세 메시지
     */
    public ServiceException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
