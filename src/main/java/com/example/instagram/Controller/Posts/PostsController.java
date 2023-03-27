package com.example.instagram.Controller.Posts;

import com.example.instagram.Dto.Feed.FeedResponseDto;
import com.example.instagram.Dto.Posts.PostsCreateRequestDto;
import com.example.instagram.Dto.Posts.PostsCreateResponseDto;
import com.example.instagram.Dto.Posts.PostsEditRequestDto;
import com.example.instagram.Dto.Posts.PostsEditResponseDto;
import com.example.instagram.Entity.User.User;
import com.example.instagram.Exception.User.NotFoundUserException;
import com.example.instagram.Repository.User.UserRepository;
import com.example.instagram.Service.Posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostsController {
    private final PostsService postsService;
    private final UserRepository userRepository;

    // 글 생성
    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public PostsCreateResponseDto createBoard(@RequestBody @Valid PostsCreateRequestDto postsCreateRequestDto) {
        return postsService.createBoard(postsCreateRequestDto, getUser());
    }

    // 글 수정
    @PutMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostsEditResponseDto editBoard(@PathVariable long id, @RequestBody @Valid PostsEditRequestDto postsEditRequestDto) {
        return postsService.editBoard(id, postsEditRequestDto, getUser());
    }

    // 글 삭제
    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBoard(@PathVariable long id) {
        postsService.deleteBoard(id, getUser());
    }

    // 피드 조회
    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public FeedResponseDto getFeed() {
        return postsService.getFeed(getUser());
    }

    // 토큰 정보로 유저 객체 생성
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByNickname(authentication.getName()).orElseThrow(NotFoundUserException::new);
    }
}
