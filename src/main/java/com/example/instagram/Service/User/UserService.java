package com.example.instagram.Service.User;

import com.example.instagram.Config.Jwt.TokenProvider;
import com.example.instagram.Dto.Token.TokenReissueRequestDto;
import com.example.instagram.Dto.Token.TokenResponseDto;
import com.example.instagram.Dto.User.*;
import com.example.instagram.Entity.Token.RefreshToken;
import com.example.instagram.Entity.User.User;
import com.example.instagram.Entity.User.UserRoleType;
import com.example.instagram.Exception.Token.NotMatchRefreshTokenException;
import com.example.instagram.Exception.User.FailureUserDeleteException;
import com.example.instagram.Exception.User.NotFoundUserException;
import com.example.instagram.Repository.Token.RefreshTokenRepository;
import com.example.instagram.Repository.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;

    // 회원가입
    @Transactional
    public UserRegisterResponseDto signUp(UserRegisterRequestDto userRegisterRequestDto) {
        User newUser = User.builder()
                .nickname(userRegisterRequestDto.getNickname())
                .profile_image_url(userRegisterRequestDto.getProfile_image())
                .role(UserRoleType.ROLE_MEMBER)
                .build();
        userRepository.save(newUser);
        return new UserRegisterResponseDto().toDo(newUser);
    }

    // 회원탈퇴
    @Transactional
    public void deleteUser(UserDeleteRequestDto userDeleteRequestDto, User user) {
        if(userDeleteRequestDto.getUser_id() == user.getId()) {
            userRepository.deleteById(user.getId());
            return ;
        }
        throw new FailureUserDeleteException();
    }

    // 로그인
    @Transactional
    public TokenResponseDto signIn(UserSignInRequestDto userSignInRequestDto) {
        User user = userRepository.findByNickname(userSignInRequestDto.getNickname()).orElseThrow(NotFoundUserException::new);
        Authentication authentication = getAuthentication(user);

        TokenResponseDto tokenResponseDto = tokenProvider.createToken(authentication, user.getId());
        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(tokenProvider.getRefreshToken())
                .build();
        refreshTokenRepository.save(refreshToken);
        return tokenResponseDto;
    }

    // 토큰 재발급
    @Transactional
    public TokenResponseDto tokenReissue(TokenReissueRequestDto tokenReissueRequestDto, User user) {
        RefreshToken refreshToken = refreshTokenRepository.findByTokenAndUser(tokenReissueRequestDto.getRefresh_token(), user)
                .orElseThrow(NotMatchRefreshTokenException::new);
        Authentication authentication = getAuthentication(user);

        TokenResponseDto tokenResponseDto = tokenProvider.createToken(authentication, user.getId());
        refreshToken.setToken(tokenProvider.getRefreshToken());
        return tokenResponseDto;
    }

    // Authentication 생성
    public UsernamePasswordAuthenticationToken getAuthentication(User user) {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole().toString());
        return new UsernamePasswordAuthenticationToken(user.getNickname(), "", Collections.singleton(simpleGrantedAuthority));
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
