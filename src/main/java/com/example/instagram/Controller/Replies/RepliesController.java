package com.example.instagram.Controller.Replies;

import com.example.instagram.Dto.Replies.RepliesCreateRequestDto;
import com.example.instagram.Dto.Replies.RepliesCreateResponseDto;
import com.example.instagram.Dto.Replies.RepliesEditRequestDto;
import com.example.instagram.Dto.Replies.RepliesEditResponseDto;
import com.example.instagram.Entity.User.User;
import com.example.instagram.Exception.User.NotFoundUserException;
import com.example.instagram.Repository.User.UserRepository;
import com.example.instagram.Service.Replies.RepliesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RepliesController {
    private final RepliesService repliesService;
    private final UserRepository userRepository;

    // 답글 생성
    @PostMapping("/replies")
    @ResponseStatus(HttpStatus.OK)
    public RepliesCreateResponseDto createReplies(@RequestBody @Valid RepliesCreateRequestDto repliesCreateRequestDto) {
        return repliesService.createReplies(repliesCreateRequestDto, getUser());
    }

    // 답글 수정
    @PutMapping("/replies")
    @ResponseStatus(HttpStatus.OK)
    public RepliesEditResponseDto editReplies(@RequestBody @Valid RepliesEditRequestDto repliesEditRequestDto) {
        return repliesService.editReplies(repliesEditRequestDto, getUser());
    }

    // 답글 삭제
    @DeleteMapping("/replies/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReplies(@PathVariable long id) {
        repliesService.deleteReplies(id, getUser());
    }

    // 토큰 정보로 유저 객체 생성
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByNickname(authentication.getName()).orElseThrow(NotFoundUserException::new);
    }
}
