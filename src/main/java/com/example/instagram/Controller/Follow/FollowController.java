package com.example.instagram.Controller.Follow;

import com.example.instagram.Dto.Follow.FollowRequestDto;
import com.example.instagram.Dto.Follow.UnFollowRequestDto;
import com.example.instagram.Service.Follow.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FollowController {
    private final FollowService followService;

    // 팔로우
    @PostMapping("/follow")
    @ResponseStatus(HttpStatus.OK)
    public void follow(@RequestBody @Valid FollowRequestDto followRequestDto) {
        followService.follow(followRequestDto.getUser_id());
    }

    // 팔로우 취소
    @DeleteMapping("/follow")
    @ResponseStatus(HttpStatus.OK)
    public void unFollow(@RequestBody @Valid UnFollowRequestDto unFollowRequestDto) {
        followService.unFollow(unFollowRequestDto.getUser_id());
    }
}
