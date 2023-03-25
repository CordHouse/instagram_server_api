package com.example.instagram.Controller.Replies;

import com.example.instagram.Dto.Replies.RepliesCreateRequestDto;
import com.example.instagram.Dto.Replies.RepliesCreateResponseDto;
import com.example.instagram.Dto.Replies.RepliesEditRequestDto;
import com.example.instagram.Dto.Replies.RepliesEditResponseDto;
import com.example.instagram.Service.Replies.RepliesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RepliesController {
    private final RepliesService repliesService;

    // 답글 생성
    @PostMapping("/replies")
    @ResponseStatus(HttpStatus.OK)
    public RepliesCreateResponseDto createReplies(@RequestBody @Valid RepliesCreateRequestDto repliesCreateRequestDto) {
        return repliesService.createReplies(repliesCreateRequestDto);
    }

    // 답글 수정
    @PutMapping("/replies")
    @ResponseStatus(HttpStatus.OK)
    public RepliesEditResponseDto editReplies(@RequestBody @Valid RepliesEditRequestDto repliesEditRequestDto) {
        return repliesService.editReplies(repliesEditRequestDto);
    }

    // 답글 삭제
    @DeleteMapping("/replies/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReplies(@PathVariable long id) {
        repliesService.deleteReplies(id);
    }
}
