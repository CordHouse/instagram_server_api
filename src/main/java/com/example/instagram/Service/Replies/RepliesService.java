package com.example.instagram.Service.Replies;

import com.example.instagram.Dto.Replies.RepliesCreateRequestDto;
import com.example.instagram.Dto.Replies.RepliesCreateResponseDto;
import com.example.instagram.Dto.Replies.RepliesEditRequestDto;
import com.example.instagram.Dto.Replies.RepliesEditResponseDto;
import com.example.instagram.Entity.Comment.Comment;
import com.example.instagram.Entity.Replies.Replies;
import com.example.instagram.Entity.User.User;
import com.example.instagram.Exception.Comment.NotFoundCommentException;
import com.example.instagram.Exception.Replies.NotFoundRepliesException;
import com.example.instagram.Repository.Comment.CommentRepository;
import com.example.instagram.Repository.Replies.RepliesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RepliesService {
    private final RepliesRepository repliesRepository;
    private final CommentRepository commentRepository;

    // 답글 생성
    @Transactional
    public RepliesCreateResponseDto createReplies(RepliesCreateRequestDto repliesCreateRequestDto, User user) {
        Comment choiceComment = commentRepository.findById(repliesCreateRequestDto.getComment_id())
                .orElseThrow(NotFoundCommentException::new);
        Replies newReplies = new Replies(repliesCreateRequestDto.getContent(), choiceComment, user);
        repliesRepository.save(newReplies);
        return new RepliesCreateResponseDto().toDo(newReplies);
    }

    // 답글 수정
    @Transactional
    public RepliesEditResponseDto editReplies(RepliesEditRequestDto repliesEditRequestDto, User user) {
        Replies editReplies = repliesRepository.findByIdAndUser(repliesEditRequestDto.getId(), user)
                .orElseThrow(NotFoundRepliesException::new);
        editReplies.setContent(repliesEditRequestDto.getContent());
        return new RepliesEditResponseDto().toDo(editReplies);
    }

    // 답글 삭제
    @Transactional
    public void deleteReplies(long id, User user) {
        repliesRepository.deleteByIdAndUser(id, user);
    }
}
