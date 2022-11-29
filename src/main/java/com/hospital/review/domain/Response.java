package com.hospital.review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
/*
Response추상화 -> 에러코드를 포함시켜 리턴하기 위함
모든 Response는 이 Response객체로 감싸서 Return
 */
@AllArgsConstructor
@Getter
public class Response<T> {
    private String resultCode;
    private T result; // 성공 시 T로 감싸서 반환

    // error, success -> static 처리 -> 어디서든 사용 가능
    public static Response<Void> error(String resultCode) {
        return new Response(resultCode, null);
    }

    // UserJoinResponse(UserName, emailAddress) 형식으로 리턴되며 어떤 데이터가 저장되는지 알 수 있음
    public static <T> Response<T> success(T result) {
        return new Response("SUCCESS", result);
    }
}
