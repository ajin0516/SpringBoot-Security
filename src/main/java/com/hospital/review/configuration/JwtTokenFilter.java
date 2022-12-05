package com.hospital.review.configuration;

import com.hospital.review.domain.User;
import com.hospital.review.service.UserService;
import com.hospital.review.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter { // OncePerRequestFilter - 매 요청마다 한번만 실행되게끔 구현한 클래스


    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 권한 주거나 안주기(입구 개념)
        // 현재는 닫혀있음

        // 언제 막아야 할까?
        // 1. 토큰을 안가지고 옴
        // 2. 적절하지 않은 토큰을 가지고 옴
        // 3. 기간이 지난 토큰을 가지고 옴

        // request 는 우리가 header에 넣는 것
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorizationHeader={}",authorizationHeader);


//        token을 header에 넣었기 떄문에 header에서 꺼내기
//        token에 Claim으로 UserName을 넣었기 때문에 token.Claim에서 꺼낼 예정
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
            log.info("헤더를 가져오는 과정에서 에러 발생. 헤더가 null이거나 잘못됨");
            filterChain.doFilter(request, response);
            return;
        }

        // token 분리
        String token;
        try {
            token = authorizationHeader.split(" ")[1];
        } catch (Exception e) {
            log.error("token 추출에 실패했습니다");
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 만료 됐는지 check
        if (JwtTokenUtil.isExpired(token, secretKey)) {
            filterChain.doFilter(request, response);
            return;
        }

        // token에서 UserName 꺼내기
        String userName = JwtTokenUtil.getUserName(token, secretKey);
        log.info("userName={}",userName);

        User user = userService.getUserByUserName(userName);
        log.info("userRole={}",user.getRole());



        // 문 열어주기(( 카드 찍으면 열리는 기능)
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), null, List.of(new SimpleGrantedAuthority(user.getRole().name())));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken); // 권한 부여
        filterChain.doFilter(request,response); // 다음 chain으로 넘기기


    }
}
