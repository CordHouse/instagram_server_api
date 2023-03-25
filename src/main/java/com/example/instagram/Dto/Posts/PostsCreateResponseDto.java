package com.example.instagram.Dto.Posts;

import com.example.instagram.Entity.Posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsCreateResponseDto {
    private long id;
    private String image;
    private String content;

    public PostsCreateResponseDto toDo(Posts posts) {
        return new PostsCreateResponseDto(posts.getId(), posts.getImage(), posts.getContent());
    }
}
