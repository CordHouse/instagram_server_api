package com.example.instagram.Controller.User;

import com.example.instagram.Dto.Token.TokenReissueRequestDto;
import com.example.instagram.Dto.Token.TokenResponseDto;
import com.example.instagram.Dto.User.*;
import com.example.instagram.Entity.User.User;
import com.example.instagram.Exception.User.NotFoundUserException;
import com.example.instagram.Repository.User.UserRepository;
import com.example.instagram.Service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    // 회원가입
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.OK)
    public UserRegisterResponseDto signUp(@RequestBody @Valid UserRegisterRequestDto userRegisterRequestDto) {
        return userService.signUp(userRegisterRequestDto);
    }

    // 회원탈퇴
    @DeleteMapping("/profile/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    // 로그인
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponseDto signIn(@RequestBody @Valid UserSignInRequestDto userSignInRequestDto) {
        return userService.signIn(userSignInRequestDto);
    }

    // 토큰 재발급
    @PostMapping("/reissue")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponseDto tokenReissue(@RequestBody @Valid TokenReissueRequestDto tokenReissueRequestDto) {
        return userService.tokenReissue(tokenReissueRequestDto, getUser());
    }

    // 프로필 조회 -> Jwt + security 적용 이후 확인
    @PostMapping("/profile/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getProfile(@PathVariable long id) {
        return userService.getProfile(id);
    }

    // 프로필 수정 -> Jwt + security 적용 이후 확인
    @PutMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public UserEditResponseDto editProfile(@RequestBody @Valid UserEditRequestDto userEditRequestDto) {
        return userService.editProfile(userEditRequestDto);
    }

    // 토큰 정보로 유저 객체 생성
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByNickname(authentication.getName()).orElseThrow(NotFoundUserException::new);
    }
}
