package com.hospital.review.controller;


import com.hospital.review.domain.dto.ReviewCreateReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
@Slf4j
public class ReviewController {

    @PostMapping()
    public String write(@RequestBody ReviewCreateReq req, Authentication authentication) {
        log.info("Controller user:{}",authentication.getName());
        return "리뷰 등록에 성공했습니다";
    }
}
