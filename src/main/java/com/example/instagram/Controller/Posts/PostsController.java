package com.example.instagram.Controller.Posts;

import com.example.instagram.Dto.Feed.FeedRequestDto;
import com.example.instagram.Dto.Feed.FeedResponseDto;
import com.example.instagram.Dto.Posts.*;
import com.example.instagram.Entity.User.User;
import com.example.instagram.Exception.User.NotFoundUserException;
import com.example.instagram.Repository.User.UserRepository;
import com.example.instagram.Service.Posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private static final int PAGE_SIZE = 2;

    // 글 생성
    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public PostsCreateResponseDto createBoard(@ModelAttribute @Valid PostsCreateRequestDto postsCreateRequestDto) {
        return postsService.createBoard(postsCreateRequestDto, getUser());
    }

    // 글 수정
    @PutMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public PostsEditResponseDto editBoard(@ModelAttribute @Valid PostsEditRequestDto postsEditRequestDto) {
        return postsService.editBoard(postsEditRequestDto, getUser());
    }

    // 글 삭제
    @DeleteMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBoard(@RequestBody @Valid PostDeleteRequestDto postDeleteRequestDto) {
        postsService.deleteBoard(postDeleteRequestDto, getUser());
    }

    // 피드 조회
    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public FeedResponseDto getFeed(@RequestBody @Valid FeedRequestDto feedRequestDto,
            @PageableDefault(size = PAGE_SIZE, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return postsService.getFeed(pageable, feedRequestDto);
    }

    // 토큰 정보로 유저 객체 생성
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByNickname(authentication.getName()).orElseThrow(NotFoundUserException::new);
    }
}
