package com.example.instagram.Service.Board;

import com.example.instagram.Dto.Board.BoardCreateRequestDto;
import com.example.instagram.Dto.Board.BoardCreateResponseDto;
import com.example.instagram.Dto.Board.BoardEditRequestDto;
import com.example.instagram.Dto.Board.BoardEditResponseDto;
import com.example.instagram.Entity.Board.Board;
import com.example.instagram.Exception.NotFoundBoardException;
import com.example.instagram.Repository.Board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 글 생성
    @Transactional
    public BoardCreateResponseDto createBoard(BoardCreateRequestDto boardCreateRequestDto) {
        Board newBoard = new Board(boardCreateRequestDto.getImage(), boardCreateRequestDto.getContent());
        boardRepository.save(newBoard);
        return new BoardCreateResponseDto().toDo(newBoard);
    }

    // 글 수정
    @Transactional
    public BoardEditResponseDto editBoard(long id, BoardEditRequestDto boardEditRequestDto) {
        Board editBoard = boardRepository.findById(id).orElseThrow(NotFoundBoardException::new);
        editBoard.setImage(boardEditRequestDto.getImage());
        editBoard.setContent(boardEditRequestDto.getContent());
        return new BoardEditResponseDto().toDo(editBoard);
    }

    // 글 삭제
    @Transactional
    public void deleteBoard(long id) {
        boardRepository.deleteById(id);
    }
}
