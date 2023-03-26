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
public class FeedResponseDto {
    private List<FeedPostsResponseDto> posts;

    public FeedResponseDto toDo(List<Posts> posts) {
        return new FeedResponseDto(
                posts.stream().map(post -> new FeedPostsResponseDto().toDo(post)).collect(Collectors.toList())
        );
    }
}
