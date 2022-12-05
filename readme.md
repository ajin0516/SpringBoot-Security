# spring Security

---

## 🖥 유저 로그인 기능

### 1. 회원가입 기능
[POST] /api/v1/users/join


### 2. Exception 처리

### 3. 회원가입 Test 작성


### 4. 리뷰 작성 기능  

[POST] /api/v1/reviews
1. ReviewController 추가

2. SecurityConfig 수정
   - 로그인, 회원가입 API등은 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
   - 나머지 API 는 전부 인증 필요하므로 authenticated() 변경

3. TokenFilter 추가
   - 권한 여부를 주는 문
   - token(X) , 올바른 token(X), 만료된 token 등을 막기 위한 기능
   - 올바른 요청이 들어오면 문 열어주는 기능
4. JwtTokenUtil 수정
   - 만료된 token인지 확인 기능 추가
   
5. User 수정
   - UserRole 추가

6. UserService 수정
   - getUserByUserName() 메서드 추가
7. JwtTokenFilter 수정
8. ReviewController 
   - 잘 작동하는지 확인


