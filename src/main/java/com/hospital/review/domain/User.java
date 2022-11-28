package com.hospital.review.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // pk - 중복 하용X, null값 허용X, 테이블에서 한개만 생성 가능
    // unique - 중복 하용X, null값 허용, 테이블에서 여러개 생성 가능
    @Column(unique = true)
    private String userName;
    private String password;
    private String emailAddress;
}
