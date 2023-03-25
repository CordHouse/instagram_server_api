package com.example.instagram.Dto.Posts;

import com.example.instagram.Entity.Posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsEditResponseDto {
    private long id;
    private String image_url;
    private String content;

    public PostsEditResponseDto toDo(Posts posts) {
        return new PostsEditResponseDto(posts.getId(), posts.getImage(), posts.getContent());
    }
}
