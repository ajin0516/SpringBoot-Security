package com.hospital.review.exception;

import com.hospital.review.domain.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/*
예외처리를 관리하는 클래스
 */
// 전역적으로 예외를 처리할 수 있는 어노테이션 (응답을 JSON으로 해줌)
@RestControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
        // RuntimeException 이 나면 controller 대시 이곳에서 리턴함
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(e.getMessage()));
        // INTERNAL_SERVER_ERROR 를 리턴하고 ResponseBody 에 e.getMessage()를 추가해서 보냄
    }

    @ExceptionHandler(HospitalReviewAppException.class)
    public ResponseEntity<?> hospitalReviewAppExceptionHandler(HospitalReviewAppException e) {
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().getMessage()));
    }
}
