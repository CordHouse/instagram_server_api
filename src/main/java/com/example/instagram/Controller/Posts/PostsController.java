package com.example.instagram.Controller.Posts;

import com.example.instagram.Dto.Feed.FeedResponseDto;
import com.example.instagram.Dto.Posts.PostsCreateRequestDto;
import com.example.instagram.Dto.Posts.PostsCreateResponseDto;
import com.example.instagram.Dto.Posts.PostsEditRequestDto;
import com.example.instagram.Dto.Posts.PostsEditResponseDto;
import com.example.instagram.Service.Posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostsController {
    private final PostsService postsService;

    // 글 생성
    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public PostsCreateResponseDto createBoard(@RequestBody @Valid PostsCreateRequestDto postsCreateRequestDto) {
        return postsService.createBoard(postsCreateRequestDto);
    }

    // 글 수정
    @PutMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostsEditResponseDto editBoard(@PathVariable long id, @RequestBody @Valid PostsEditRequestDto postsEditRequestDto) {
        return postsService.editBoard(id, postsEditRequestDto);
    }

    // 글 삭제
    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBoard(@PathVariable long id) {
        postsService.deleteBoard(id);
    }

    // 피드 조회
    @GetMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FeedResponseDto getFeed(@PathVariable long id) {
        return postsService.getFeed(id);
    }
}
