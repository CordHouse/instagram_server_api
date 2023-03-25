package com.example.instagram.Dto.Feed;

import com.example.instagram.Entity.Comment.Comment;
import com.example.instagram.Entity.Feed.Feed;
import com.example.instagram.Entity.Posts.Posts;
import com.example.instagram.Entity.Replies.Replies;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedResponseDto {
    private Posts posts;
    private Comment comments;
    private Replies replies;

    public FeedResponseDto toDo(Feed feed) {
        return new FeedResponseDto(feed.getPosts(), feed.getComment(), feed.getReplies());
    }
}
