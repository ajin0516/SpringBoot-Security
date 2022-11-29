package com.hospital.review.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
/*
enum : 미리 지정 해놓고 그 값 말고 다른 값들을 넣지 못하게 하여
예측한 범위 내에서 프로그램이 작동하도록 하기 위한 기능
 */
@AllArgsConstructor
@Getter
public enum ErrorCode { // 발생하는 에러코드를 미리 담아놓는 클래스

    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "User name is duplicated."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "UserName Not Found"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "");
    private HttpStatus status;
    private String message;

}
