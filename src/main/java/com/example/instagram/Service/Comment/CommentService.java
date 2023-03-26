package com.example.instagram.Service.Comment;

import com.example.instagram.Dto.Comment.CommentCreateRequestDto;
import com.example.instagram.Dto.Comment.CommentCreateResponseDto;
import com.example.instagram.Dto.Comment.CommentEditRequestDto;
import com.example.instagram.Dto.Comment.CommentEditResponseDto;
import com.example.instagram.Entity.Comment.Comment;
import com.example.instagram.Entity.Posts.Posts;
import com.example.instagram.Entity.User.User;
import com.example.instagram.Exception.Comment.NotFoundCommentException;
import com.example.instagram.Exception.Posts.NotFoundPostsException;
import com.example.instagram.Exception.User.NotFoundUserException;
import com.example.instagram.Repository.Comment.CommentRepository;
import com.example.instagram.Repository.Posts.PostsRepository;
import com.example.instagram.Repository.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    // 댓글 생성
    @Transactional
    public CommentCreateResponseDto createComment(CommentCreateRequestDto commentCreateRequestDto) {
        User user = userRepository.findById(1L).orElseThrow(NotFoundUserException::new);
        Posts posts_id = getPost_id(commentCreateRequestDto.getPost_id());
        Comment newComment = new Comment(posts_id, commentCreateRequestDto.getContent(), user);
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
    public Posts getPost_id(long id) {
        return postsRepository.findById(id).orElseThrow(NotFoundPostsException::new);
    }
}
