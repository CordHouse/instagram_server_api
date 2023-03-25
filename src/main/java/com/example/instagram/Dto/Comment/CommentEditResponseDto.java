package com.example.instagram.Dto.Comment;

import com.example.instagram.Entity.Comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEditResponseDto {
    private long id;
    private String content;

    public CommentEditResponseDto toDo(Comment comment) {
        return new CommentEditResponseDto(comment.getId(), comment.getContent());
    }
}
