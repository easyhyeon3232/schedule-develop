package com.example.scheduledevelop.config;

import com.example.scheduledevelop.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 애플리케이션 전역에서 발생하는 예외를 가로채어 공통으로 된 응답 형식을 반환하는 핸들러입니다.
 * {@link RestControllerAdvice} 를 사용하여  모든 컨트롤러에서 던져지는 예외를 처리합니다.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 서비스 클래스에서 의도적으로 던진 {@link ServiceException}을 처리합니다.
     * 예외 객체에 포함된 상태 코드(Status)와 메시지를 사용하여 응답을 생성합니다.
     * @param e 서비스 로직 처리 중 발생한 커스텀 예외 객체
     * @return 예외에 지정된 HTTP 상태 코드와 메시지를 포함한 {@link ResponseEntity}
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> handleServiceException(ServiceException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(e.getMessage());
    }
}
