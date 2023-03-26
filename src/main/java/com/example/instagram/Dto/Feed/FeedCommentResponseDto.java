package com.example.instagram.Dto.Feed;

import com.example.instagram.Entity.Comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedCommentResponseDto {
    private long id;
    private String content;
    private String nickname;
    private String profile_image_url;
    private List<FeedRepliesResponseDto> replies;

    public FeedCommentResponseDto toDo(Comment comment) {
        return new FeedCommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getNickname(),
                comment.getUser().getProfile_image_url(),
                comment.getReplies().stream().map(replies -> new FeedRepliesResponseDto().toDo(replies)).collect(Collectors.toList())
        );
    }
}
