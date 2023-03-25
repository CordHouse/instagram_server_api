package com.example.instagram.Controller.User;

import com.example.instagram.Dto.User.*;
import com.example.instagram.Service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public UserRegisterResponseDto register(@RequestBody @Valid UserRegisterRequestDto userRegisterRequestDto) {
        return userService.register(userRegisterRequestDto);
    }

    // 회원탈퇴
    @DeleteMapping("/profile/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
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
}
