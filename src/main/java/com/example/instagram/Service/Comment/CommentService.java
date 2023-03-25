package com.example.instagram.Service.Comment;

import com.example.instagram.Dto.Comment.CommentCreateRequestDto;
import com.example.instagram.Dto.Comment.CommentCreateResponseDto;
import com.example.instagram.Dto.Comment.CommentEditRequestDto;
import com.example.instagram.Dto.Comment.CommentEditResponseDto;
import com.example.instagram.Entity.Board.Board;
import com.example.instagram.Entity.Comment.Comment;
import com.example.instagram.Exception.NotFoundBoardException;
import com.example.instagram.Exception.NotFoundCommentException;
import com.example.instagram.Repository.Board.BoardRepository;
import com.example.instagram.Repository.Comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    // 댓글 생성
    @Transactional
    public CommentCreateResponseDto createComment(CommentCreateRequestDto commentCreateRequestDto) {
        Board board_id = getPost_id(commentCreateRequestDto.getPost_id());
        Comment newComment = new Comment(board_id, commentCreateRequestDto.getContent());
        commentRepository.save(newComment);
        return new CommentCreateResponseDto().toDo(newComment);
    }

    // 댓글 수정
    @Transactional
    public CommentEditResponseDto editComment(CommentEditRequestDto commentEditRequestDto) {
        Comment editComment = commentRepository.findById(commentEditRequestDto.getId()).orElseThrow(NotFoundCommentException::new);
        editComment.setContent(commentEditRequestDto.getContent());
        return new CommentEditResponseDto().toDo(editComment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    // 게시글 작성자 id(pk)로 검색
    public Board getPost_id(long id) {
        return boardRepository.findById(id).orElseThrow(NotFoundBoardException::new);
    }
}
