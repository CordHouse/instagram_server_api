package com.example.instagram.Controller.Board;

import com.example.instagram.Dto.Board.BoardCreateRequestDto;
import com.example.instagram.Dto.Board.BoardCreateResponseDto;
import com.example.instagram.Dto.Board.BoardEditRequestDto;
import com.example.instagram.Dto.Board.BoardEditResponseDto;
import com.example.instagram.Service.Board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    // 글 생성
    @RequestMapping("/create/board")
    @ResponseStatus(HttpStatus.OK)
    public BoardCreateResponseDto createBoard(@RequestBody @Valid BoardCreateRequestDto boardCreateRequestDto) {
        return boardService.createBoard(boardCreateRequestDto);
    }

    // 글 수정
    @RequestMapping("/edit/board/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BoardEditResponseDto editBoard(@PathVariable long id, @RequestBody @Valid BoardEditRequestDto boardEditRequestDto) {
        return boardService.editBoard(id, boardEditRequestDto);
    }

    // 글 삭제
    @RequestMapping("/delete/board/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBoard(@PathVariable long id) {
        boardService.deleteBoard(id);
    }
}
