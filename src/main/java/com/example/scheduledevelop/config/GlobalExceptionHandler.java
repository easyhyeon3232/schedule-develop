package com.example.scheduledevelop.config;

import com.example.scheduledevelop.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    /**
     * 잘못된 인자가 전달되었을 때 발생하는 {@link IllegalArgumentException}를 처리합니다.
     * @param e 잘못된 인자 전달로 인해 발생한 예외 객체
     * @return HTTP 400 상태 코드와 예외 메시지를 포함한 {@link ResponseEntity}
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    // 2. @Valid 어노테이션 검증 실패 에러 잡기 (@Pattern, @NotBlank 등)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
        // 어노테이션에 적어둔 message만 쏙 뽑아서 반환
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

}
