# spring Security

---

## ๐ฅ ์ ์  ๋ก๊ทธ์ธ ๊ธฐ๋ฅ

### 1. ํ์๊ฐ์ ๊ธฐ๋ฅ
[POST] /api/v1/users/join


### 2. Exception ์ฒ๋ฆฌ

### 3. ํ์๊ฐ์ Test ์์ฑ


### 4. ๋ฆฌ๋ทฐ ์์ฑ ๊ธฐ๋ฅ  

[POST] /api/v1/reviews
1. ReviewController ์ถ๊ฐ

2. SecurityConfig ์์ 
   - ๋ก๊ทธ์ธ, ํ์๊ฐ์ API๋ฑ์ ํ ํฐ์ด ์๋ ์ํ์์ ์์ฒญ์ด ๋ค์ด์ค๊ธฐ ๋๋ฌธ์ permitAll ์ค์ 
   - ๋๋จธ์ง API ๋ ์ ๋ถ ์ธ์ฆ ํ์ํ๋ฏ๋ก authenticated() ๋ณ๊ฒฝ

3. TokenFilter ์ถ๊ฐ
   - ๊ถํ ์ฌ๋ถ๋ฅผ ์ฃผ๋ ๋ฌธ
   - token(X) , ์ฌ๋ฐ๋ฅธ token(X), ๋ง๋ฃ๋ token ๋ฑ์ ๋ง๊ธฐ ์ํ ๊ธฐ๋ฅ
   - ์ฌ๋ฐ๋ฅธ ์์ฒญ์ด ๋ค์ด์ค๋ฉด ๋ฌธ ์ด์ด์ฃผ๋ ๊ธฐ๋ฅ
4. JwtTokenUtil ์์ 
   - ๋ง๋ฃ๋ token์ธ์ง ํ์ธ ๊ธฐ๋ฅ ์ถ๊ฐ
   
5. User ์์ 
   - UserRole ์ถ๊ฐ

6. UserService ์์ 
   - getUserByUserName() ๋ฉ์๋ ์ถ๊ฐ
7. JwtTokenFilter ์์ 
8. ReviewController 
   - ์ ์๋ํ๋์ง ํ์ธ


