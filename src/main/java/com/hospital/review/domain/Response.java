package com.hospital.review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response<T> {
    private String resultCode;
    private T result; // 성공 반환 시 T로 감싸서 반

    private static Response<Void> error(String resultCode) {
        return new Response(resultCode, null);
    }

    public static <T> Response<T> success(T result) {
        return new Response("SUCCESS", result);
    }
}
