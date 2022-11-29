package com.hospital.review.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinResponse {

    // result는 보안으로 인해 password 제외하고 출력
    private String userName;
    private String email;

}
