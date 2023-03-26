package com.example.instagram.Dto.Feed;

import com.example.instagram.Entity.Posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedPostsResponseDto {
    private long id;
    private String content;
    private String image_url;
    private List<FeedCommentResponseDto> comments;

    public FeedPostsResponseDto toDo(Posts posts) {
        return new FeedPostsResponseDto(
                posts.getId(),
                posts.getContent(),
                posts.getImage(),
                posts.getComments().stream().map(comment -> new FeedCommentResponseDto().toDo(comment)).collect(Collectors.toList())
        );
    }
}
