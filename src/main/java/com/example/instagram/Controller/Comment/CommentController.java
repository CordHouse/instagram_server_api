package com.example.instagram.Controller.Comment;

import com.example.instagram.Dto.Comment.CommentCreateRequestDto;
import com.example.instagram.Dto.Comment.CommentCreateResponseDto;
import com.example.instagram.Dto.Comment.CommentEditRequestDto;
import com.example.instagram.Dto.Comment.CommentEditResponseDto;
import com.example.instagram.Entity.User.User;
import com.example.instagram.Exception.User.NotFoundUserException;
import com.example.instagram.Repository.User.UserRepository;
import com.example.instagram.Service.Comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    private final UserRepository userRepository;

    // 댓글 생성
    @PostMapping("/comment")
    @ResponseStatus(HttpStatus.OK)
    public CommentCreateResponseDto createComment(@RequestBody @Valid CommentCreateRequestDto commentCreateRequestDto) {
        return commentService.createComment(commentCreateRequestDto, getUser());
    }

    // 댓글 수정
    @PutMapping("/comment")
    @ResponseStatus(HttpStatus.OK)
    public CommentEditResponseDto editComment(@RequestBody @Valid CommentEditRequestDto commentEditRequestDto) {
        return commentService.editComment(commentEditRequestDto, getUser());
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable long id) {
        commentService.deleteComment(id, getUser());
    }

    // 토큰 정보로 유저 객체 생성
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByNickname(authentication.getName()).orElseThrow(NotFoundUserException::new);
    }
}
