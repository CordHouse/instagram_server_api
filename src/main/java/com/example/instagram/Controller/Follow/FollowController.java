package com.example.instagram.Controller.Follow;

import com.example.instagram.Dto.Follow.FollowRequestDto;
import com.example.instagram.Dto.Follow.UnFollowRequestDto;
import com.example.instagram.Entity.User.User;
import com.example.instagram.Exception.User.NotFoundUserException;
import com.example.instagram.Repository.User.UserRepository;
import com.example.instagram.Service.Follow.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FollowController {
    private final FollowService followService;
    private final UserRepository userRepository;

    // 팔로우
    @PostMapping("/follow")
    @ResponseStatus(HttpStatus.OK)
    public void follow(@RequestBody @Valid FollowRequestDto followRequestDto) {
        followService.follow(followRequestDto.getUser_id(), getUser());
    }

    // 팔로우 취소
    @DeleteMapping("/follow")
    @ResponseStatus(HttpStatus.OK)
    public void unFollow(@RequestBody @Valid UnFollowRequestDto unFollowRequestDto) {
        followService.unFollow(unFollowRequestDto.getUser_id(), getUser());
    }

    // 토큰 정보로 유저 객체 생성
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByNickname(authentication.getName()).orElseThrow(NotFoundUserException::new);
    }
}
