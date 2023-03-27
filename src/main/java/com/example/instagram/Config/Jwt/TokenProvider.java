package com.example.instagram.Config.Jwt;

import com.example.instagram.Dto.Token.TokenResponseDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j // log 출력시 사용하기 위한 어노테이션
public class TokenProvider implements InitializingBean {
    private static final String USER_ID = "user_id";
    private static final String TOKEN_VALID_TIME = "token_valid";
    private static final String REFRESH_TOKEN_VALID_TIME = "refresh_token_valid";
    private Key key;
    private String secret;
    private Long tokenValidationTime;
    private Long refreshTokenValidationTime;
    private String refresh_token;

    // build.gradle -> jwt 의존성 추가해주어야 가능하다.
    // secret은 보안되어야 하기 때문에 application.yml 에서 작성 후 적용
    // secret 정보로 key를 암호화하는 과정
    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] secret_key = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(secret_key);
    }

    // TokenProvider 생성자
    // 만료시간 또한 application.yml 정의
    // @Value 는 beans path로 선택
    // tokenValidationTime -> 3600으로 설정
    public TokenProvider(@Value("${jwt.tokenValidationTime}") long tokenValidationTime,
                         @Value("${jwt.secret}") String secret) {
        this.secret = secret;
        this.tokenValidationTime = tokenValidationTime * 2 * 1000;
        this.refreshTokenValidationTime = tokenValidationTime * 24 * 7 * 1000;
    }

    // 토큰 생성
    public TokenResponseDto createToken(Authentication authentication, long user_id) {
        long now = (new Date()).getTime();

        Date expirationTime = new Date(now + tokenValidationTime);
        Date refreshTokenExpirationTime = new Date(now + refreshTokenValidationTime);

        String access_token = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(expirationTime)
                .claim(USER_ID, user_id)
                .claim(TOKEN_VALID_TIME, expirationTime)
                .claim(REFRESH_TOKEN_VALID_TIME, refreshTokenExpirationTime)
                .signWith(this.key, SignatureAlgorithm.HS512)
                .compact();

        refresh_token = Jwts.builder()
                .setExpiration(refreshTokenExpirationTime)
                .signWith(this.key, SignatureAlgorithm.HS512)
                .compact();

        return new TokenResponseDto().toDo(access_token);
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    // 토큰을 통해 Authentication 객체 생성
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(this.key)
                .build()
                .parseClaimsJwt(token)
                .getBody();

        // 여기서 User 클래스는 UserDetail path를 선택
        User principal = new User(claims.getSubject(), null, null);

        return new UsernamePasswordAuthenticationToken(principal, "");
    }

    // 토큰 검증
    public boolean validationToken(String token) {
        try {
            // 토큰이 만들어지면 검증된 토큰이다.
            Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token);
            return true;
        } catch(SecurityException | MalformedJwtException e) {
            log.info("잘못된 토큰입니다.");
        } catch(ExpiredJwtException e) {
            log.info("이미 만료된 토큰입니다.");
        } catch(UnsupportedJwtException e) {
            log.info("지원하지 않는 토큰입니다.");
        } catch(IllegalArgumentException e) {
            log.info("토큰이 잘못되었습니다.");
        }
        return false;
    }
}
