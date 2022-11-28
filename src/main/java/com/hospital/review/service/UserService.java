package com.hospital.review.service;

import com.hospital.review.domain.User;
import com.hospital.review.domain.dto.UserDto;
import com.hospital.review.domain.dto.UserJoinRequest;
import com.hospital.review.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto join(UserJoinRequest request) {
        // 비즈니스 로직 - 회원 가입
        // 회원 userName(id) 중복 Check
        // 중복이면 회원가입 x --> Exception(예외)발생
//        userRepository.findByUserName(request.getUserName())
//                .orElseThrow(() -> new RuntimeException("해당 UserName은 중복 됩니다."));
        // 있으면 에러처리
        userRepository.findByUserName(request.getUserName())
                .ifPresent(user ->{
                    throw new RuntimeException("해당 UserName은 중복 됩니다.");
                });

        // 회원가입 .save
        User saveUser = userRepository.save(request.toEntity());
        return UserDto.builder()
                .id(saveUser.getId())
                .userName(saveUser.getUserName())
                .email(saveUser.getEmailAddress())
                .build();
    }
}
