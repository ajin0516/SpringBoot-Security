package com.hospital.review.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class UserDto { // User 변수는 동일하나 직접적으로 사용하지 않기 위해
    private Long id;
    private String userName;
    private String password;
    private String email;
}
