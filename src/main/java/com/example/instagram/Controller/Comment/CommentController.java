package com.example.instagram.Controller.Comment;

import com.example.instagram.Dto.Comment.CommentCreateRequestDto;
import com.example.instagram.Dto.Comment.CommentCreateResponseDto;
import com.example.instagram.Dto.Comment.CommentEditRequestDto;
import com.example.instagram.Dto.Comment.CommentEditResponseDto;
import com.example.instagram.Service.Comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/comment")
    @ResponseStatus(HttpStatus.OK)
    public CommentCreateResponseDto createComment(@RequestBody @Valid CommentCreateRequestDto commentCreateRequestDto) {
        return commentService.createComment(commentCreateRequestDto);
    }

    // 댓글 수정
    @PutMapping("/comment")
    @ResponseStatus(HttpStatus.OK)
    public CommentEditResponseDto editComment(@RequestBody @Valid CommentEditRequestDto commentEditRequestDto) {
        return commentService.editComment(commentEditRequestDto);
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable long id) {
        commentService.deleteComment(id);
    }
}
