package com.hospital.review.service;

import com.hospital.review.domain.User;
import com.hospital.review.domain.dto.UserDto;
import com.hospital.review.domain.dto.UserJoinRequest;
import com.hospital.review.exception.ErrorCode;
import com.hospital.review.exception.HospitalReviewAppException;
import com.hospital.review.repository.UserRepository;
import com.hospital.review.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String secretKey;
    private long expireTimeMs = 1000 * 60 * 60;


    public UserDto join(UserJoinRequest request) {
        // 비즈니스 로직 - 회원 가입
        // 회원 userName(id) 중복 Check
        // 중복이면 회원가입 x --> Exception(예외)발생
        /*
        500에러남 - 사용자 입장에서 id가 중복인지 서버 에러인지 알 수 없음 -> custom Error선언
         */
//        userRepository.findByUserName(request.getUserName())
//                .orElseThrow(() -> new RuntimeException("해당 UserName은 중복 됩니다."));
////         있으면 에러처리
//        userRepository.findByUserName(request.getUserName())
//                .ifPresent(user ->{
//                    throw new RuntimeException("해당 UserName은 중복입니다.");
//                });

        userRepository.findByUserName(request.getUserName())
                .ifPresent(user ->{
                    throw new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME,
                            String.format("username:%s", request.getUserName()));
                });

        // 회원가입 .save
        // 암호화해서 저장하기
        User saveUser = userRepository.save(request.toEntity(encoder.encode(request.getPassword())));
        return UserDto.builder()
                .id(saveUser.getId())
                .userName(saveUser.getUserName())
                .email(saveUser.getEmailAddress())
                .build();
    }

    public String login(String userName, String password){
        // userName 있는지 여부 확인
        // 없으면 not found 에러
        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new HospitalReviewAppException(ErrorCode.NOT_FOUND, String.format("%s는 가입된적이 없습니다", userName)));

        // password 있는지 여부 확인
       if(! encoder.matches(password, user.getPassword())){
           throw new HospitalReviewAppException(ErrorCode.INVALID_PASSWORD,String.format("userName 또는 password가 잘못됐습니다"));
       }

        // 두가지 확인 중 예외 만났으면 Token 발행
        return JwtTokenUtil.createToken(userName,secretKey,expireTimeMs);
    }

}
