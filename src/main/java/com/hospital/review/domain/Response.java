package com.hospital.review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
/*
Response추상화
에러코드를 포함시켜 리턴하기 위함
-> 모든 Response는 이 Response객체로 감싸서 Return
 */
@AllArgsConstructor
@Getter
public class Response<T> {
    private String resultCode;
    private T result; // 성공 시 T로 감싸서 반환

    private static Response<Void> error(String resultCode) {
        return new Response(resultCode, null);
    }

    public static <T> Response<T> success(T result) {
        return new Response("SUCCESS", result);
    }
}
