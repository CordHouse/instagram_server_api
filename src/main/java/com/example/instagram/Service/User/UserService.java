package com.example.instagram.Service.User;

import com.example.instagram.Config.Jwt.TokenProvider;
import com.example.instagram.Dto.Token.TokenReissueRequestDto;
import com.example.instagram.Dto.Token.TokenResponseDto;
import com.example.instagram.Dto.User.*;
import com.example.instagram.Entity.Token.RefreshToken;
import com.example.instagram.Entity.User.User;
import com.example.instagram.Exception.Token.NotMatchRefreshTokenException;
import com.example.instagram.Exception.User.NotFoundUserException;
import com.example.instagram.Repository.Token.RefreshTokenRepository;
import com.example.instagram.Repository.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    // 회원가입
    @Transactional
    public UserRegisterResponseDto signUp(UserRegisterRequestDto userRegisterRequestDto) {
        User newUser = new User(userRegisterRequestDto.getNickname(), userRegisterRequestDto.getProfile_image());
        userRepository.save(newUser);
        return new UserRegisterResponseDto().toDo(newUser);
    }

    // 회원탈퇴
    @Transactional
    public void deleteUser(User user) {
        userRepository.deleteById(user.getId());
    }

    // 로그인
    @Transactional
    public TokenResponseDto signIn(UserSignInRequestDto userSignInRequestDto) {
        User user = userRepository.findByNickname(userSignInRequestDto.getNickname()).orElseThrow(NotFoundUserException::new);
        Authentication authentication = getAuthentication(user);

        TokenResponseDto tokenResponseDto = tokenProvider.createToken(authentication, user.getId());
        RefreshToken refreshToken = new RefreshToken(tokenProvider.getRefreshToken(), user);
        refreshTokenRepository.save(refreshToken);
        return tokenResponseDto;
    }

    // 토큰 재발급
    @Transactional
    public TokenResponseDto tokenReissue(TokenReissueRequestDto tokenReissueRequestDto, User user) {
        RefreshToken refreshToken = refreshTokenRepository.findByTokenAndUser(tokenReissueRequestDto.getRefresh_token(), user)
                .orElseThrow(NotMatchRefreshTokenException::new);
        UsernamePasswordAuthenticationToken authToken = getAuthentication(user);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authToken);

        TokenResponseDto tokenResponseDto = tokenProvider.createToken(authentication, user.getId());
        refreshToken.setToken(tokenProvider.getRefreshToken());
        return tokenResponseDto;
    }

    // Authentication 생성
    public UsernamePasswordAuthenticationToken getAuthentication(User user) {
        return new UsernamePasswordAuthenticationToken(user.getNickname(), "");
    }

    // 프로필 조회
    @Transactional
    public UserResponseDto getProfile(User user) {
        return new UserResponseDto().toDo(user);
    }

    // 프로필 수정
    @Transactional
    public UserEditResponseDto editProfile(UserEditRequestDto userEditRequestDto, User user) {
        user.setNickname(userEditRequestDto.getNickname());
        user.setProfile_image_url(userEditRequestDto.getProfile_image_url());
        return new UserEditResponseDto().toDo(user);
    }
}
